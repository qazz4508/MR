package com.zq.jz.ui.model;

import com.zq.jz.MyApplication;
import com.zq.jz.db.JzDB;
import com.zq.jz.db.dao.UserInComePayTypeDao;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.db.table.UserInComePayType;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserInfoModel {

    private final JzDB mJzDB;
    private final UserInComePayTypeDao mUserInComePayTypeDao;


    public UserInfoModel() {
        mJzDB = JzDB.getInstance(MyApplication.getAppContext());
        mUserInComePayTypeDao = mJzDB.getUserInComePayTypeDao();
    }

    public Disposable insertUserType(UserInComePayType userInComePayType, OnGetDataListener<String> listener) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                mUserInComePayTypeDao.insert(userInComePayType);
                emitter.onNext("succ");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String o) throws Exception {
                        listener.onSuccess(o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }

    public Disposable getUserType(int type, OnGetDataListener<List<UserInComePayType>> listener) {
        return mUserInComePayTypeDao.getFromType(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<UserInComePayType>>() {
                    @Override
                    public void accept(List<UserInComePayType> userInComePayTypes) throws Exception {
                        listener.onSuccess(userInComePayTypes);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }
}
