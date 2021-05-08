package com.zq.jz.ui.model;

import com.zq.jz.MyApplication;
import com.zq.jz.bean.InComeSection;
import com.zq.jz.db.dao.InComePayDao;
import com.zq.jz.db.dao.InComePayTypeDao;
import com.zq.jz.db.dao.UserInComePayTypeDao;
import com.zq.jz.db.table.BillType;
import com.zq.jz.db.dao.BillTypeDao;
import com.zq.jz.db.JzDB;
import com.zq.jz.db.listener.OnDBListener;
import com.zq.jz.db.table.InComePay;
import com.zq.jz.db.table.IncomePayType;
import com.zq.jz.db.table.UserInComePayType;

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

public class DbModel {

    private final JzDB mJzDB;
    private final BillTypeDao mBillTypeDao;
    private final InComePayTypeDao mInComeTypeDao;
    private final InComePayDao mInComeDao;
    private final UserInComePayTypeDao mUserInComePayTypeDao;

    public DbModel() {
        mJzDB = JzDB.getInstance(MyApplication.getAppContext());
        mBillTypeDao = mJzDB.getBillTypeDao();
        mInComeTypeDao = mJzDB.getInComePayTypeDao();
        mInComeDao = mJzDB.getInComePayDao();
        mUserInComePayTypeDao = mJzDB.getUserInComePayTypeDao();
    }

    public Disposable getBillTypes(OnDBListener<List<BillType>> listener) {
        return mBillTypeDao.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BillType>>() {
                    @Override
                    public void accept(List<BillType> billTypes) throws Exception {
                        listener.onSuccess(billTypes);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }

    public Disposable getIncomeTypes(OnDBListener<List<IncomePayType>> listener) {
        return mInComeTypeDao.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<IncomePayType>>() {
                    @Override
                    public void accept(List<IncomePayType> billTypes) throws Exception {
                        listener.onSuccess(billTypes);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }

    public Disposable getIncomes(OnDBListener<List<InComePay>> listener) {
        return mInComeDao.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<InComePay>>() {
                    @Override
                    public void accept(List<InComePay> billTypes) throws Exception {
                        listener.onSuccess(billTypes);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }

    public Disposable getIncomePage(int type, OnDBListener<List<InComeSection>> listener) {
        return Observable.create(new ObservableOnSubscribe<List<InComeSection>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<InComeSection>> emitter) throws Exception {
                List<InComeSection> list = new ArrayList<>();
                List<IncomePayType> types = mInComeTypeDao.getFromType(type);
                for (IncomePayType incomeType : types) {
                    InComeSection head = new InComeSection(true, incomeType.getName());
                    list.add(head);
                    List<InComePay> inComePays = mInComeDao.queryFromIdSync(incomeType.getId());
                    for (InComePay inComePay : inComePays) {
                        InComeSection income = new InComeSection(inComePay);
                        list.add(income);
                    }
                }

                emitter.onNext(list);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<InComeSection>>() {
                    @Override
                    public void accept(List<InComeSection> inComeSections) throws Exception {
                        if (inComeSections.isEmpty()) {
                            listener.onEmpty();
                        } else {
                            listener.onSuccess(inComeSections);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }

    public Disposable insertUserType(UserInComePayType userInComePayType, OnDBListener<String> listener) {
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

    public Disposable getUserType(int type, OnDBListener<List<UserInComePayType>> listener) {
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
