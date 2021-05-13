package com.zq.jz.ui.model;

import android.annotation.SuppressLint;

import com.zq.jz.MyApplication;
import com.zq.jz.db.JzDB;
import com.zq.jz.db.dao.UserAccountDao;
import com.zq.jz.db.dao.UserConfigDao;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.db.table.UserAccount;
import com.zq.jz.db.table.UserConfig;
import com.zq.jz.util.LogUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserConfigModel {

    private final UserConfigDao mConfigDao;
    private final UserAccountDao mUserAccountDao;

    public UserConfigModel() {
        JzDB jzDB = JzDB.getInstance(MyApplication.getAppContext());
        mConfigDao = jzDB.getUserConfigDao();
        mUserAccountDao = jzDB.getUserAccountDao();
    }

    public Disposable getUserConfig(OnGetDataListener<UserConfig> listener) {
        return mConfigDao.get().observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserConfig>() {
                    @Override
                    public void accept(UserConfig userConfig) throws Exception {
                        listener.onSuccess(userConfig);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }

    public Disposable updateUserConfig(UserConfig userConfig) {
        return Observable.create(new ObservableOnSubscribe<UserConfig>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<UserConfig> emitter) throws Exception {
                mConfigDao.update(userConfig);
                emitter.onNext(userConfig);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserConfig>() {
                    @Override
                    public void accept(UserConfig s) throws Exception {
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    public Disposable updateSelectAccount(int accountId) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @SuppressLint("CheckResult")
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> emitter) throws Exception {
                UserConfig config = mConfigDao.getSync();
                if (config == null) {
                    UserConfig userConfig = new UserConfig();
                    userConfig.setSelectAccountId(accountId);
                    mConfigDao.insert(userConfig);
                    emitter.onNext(true);
                    return;
                }
                config.setSelectAccountId(accountId);
                updateUserConfig(config);
                emitter.onNext(true);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                    }
                });
    }


    public Disposable getSelectAccount(OnGetDataListener<UserAccount> listener) {
        return Observable.create(new ObservableOnSubscribe<UserAccount>() {
            @SuppressLint("CheckResult")
            @Override
            public void subscribe(@NonNull ObservableEmitter<UserAccount> emitter) throws Exception {
                UserConfig config = mConfigDao.getSync();
                if (config == null) {
                    emitter.onError(new RuntimeException("还没有创建账户"));
                    return;
                }
                UserAccount userAccount = mUserAccountDao.getFromId(config.getSelectAccountId());
                emitter.onNext(userAccount);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserAccount>() {
                    @Override
                    public void accept(UserAccount userAccount) throws Exception {
                        listener.onSuccess(userAccount);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }
}
