package com.zq.jz.ui.model;

import android.annotation.SuppressLint;

import com.zq.jz.MyApplication;
import com.zq.jz.db.JzDB;
import com.zq.jz.db.dao.UserAccountDao;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.db.table.UserAccount;
import com.zq.jz.ui.contract.AddUserAccountContract;

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

    public UserAccountModel() {
        JzDB jzDB = JzDB.getInstance(MyApplication.getAppContext());
        mUserAccountDao = jzDB.getUserAccountDao();
    }

    @SuppressLint("CheckResult")
    @Override
    public Disposable insert(UserAccount userAccount, OnGetDataListener<String> listener) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                mUserAccountDao.insert(userAccount);
                emitter.onNext("succ");
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
}
