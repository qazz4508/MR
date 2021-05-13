package com.zq.jz.ui.model;

import android.annotation.SuppressLint;

import com.zq.jz.MyApplication;
import com.zq.jz.bean.AccountBean;
import com.zq.jz.db.JzDB;
import com.zq.jz.db.dao.UserAccountDao;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.db.table.UserAccount;
import com.zq.jz.ui.contract.AddUserAccountContract;
import com.zq.jz.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserAccountModel implements AddUserAccountContract.Model {

    private final UserAccountDao mUserAccountDao;
    private final UserConfigModel mUserConfigModel;

    public UserAccountModel() {
        JzDB jzDB = JzDB.getInstance(MyApplication.getAppContext());
        mUserAccountDao = jzDB.getUserAccountDao();
        mUserConfigModel = new UserConfigModel();
    }

    public boolean hasAddAccount() {
        return !mUserAccountDao.getAll().isEmpty();
    }

    @SuppressLint("CheckResult")
    @Override
    public Disposable insert(UserAccount userAccount, OnGetDataListener<String> listener) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                boolean hasAddAccount = hasAddAccount();
                mUserAccountDao.insert(userAccount);
                emitter.onNext("succ");
                if (!hasAddAccount) {
                    UserAccount one = mUserAccountDao.getOne();
                    mUserConfigModel.updateSelectAccount(one.getId());
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        listener.onSuccess(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }

    public Disposable getUserAccount(OnGetDataListener<List<AccountBean>> listener) {
        return Observable.create(new ObservableOnSubscribe<List<AccountBean>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<AccountBean>> emitter) throws Exception {
                List<AccountBean> accountBeans = new ArrayList<>();
                List<UserAccount> all = mUserAccountDao.getAll();
                for (UserAccount userAccount : all) {
                    AccountBean accountBean = new AccountBean();
                    accountBean.setUserAccount(userAccount);
                    accountBeans.add(accountBean);
                }

                emitter.onNext(accountBeans);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<AccountBean>>() {
                    @Override
                    public void accept(List<AccountBean> list) throws Exception {
                        listener.onSuccess(list);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }
}
