package com.zq.jz.ui.model;

import com.zq.jz.MyApplication;
import com.zq.jz.bean.InComeSection;
import com.zq.jz.db.dao.InComeDao;
import com.zq.jz.db.dao.InComeTypeDao;
import com.zq.jz.db.table.BillType;
import com.zq.jz.db.dao.BillTypeDao;
import com.zq.jz.db.JzDB;
import com.zq.jz.db.listener.OnDBListener;
import com.zq.jz.db.table.InCome;
import com.zq.jz.db.table.IncomeType;

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
    private final InComeTypeDao mInComeTypeDao;
    private final InComeDao mInComeDao;

    public DbModel() {
        mJzDB = JzDB.getInstance(MyApplication.getAppContext());
        mBillTypeDao = mJzDB.billTypeDao();
        mInComeTypeDao = mJzDB.incomeTypeDao();
        mInComeDao = mJzDB.incomeDao();
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

    public Disposable getIncomeTypes(OnDBListener<List<IncomeType>> listener) {
        return mInComeTypeDao.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<IncomeType>>() {
                    @Override
                    public void accept(List<IncomeType> billTypes) throws Exception {
                        listener.onSuccess(billTypes);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }

    public Disposable getIncomes(OnDBListener<List<InCome>> listener) {
        return mInComeDao.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<InCome>>() {
                    @Override
                    public void accept(List<InCome> billTypes) throws Exception {
                        listener.onSuccess(billTypes);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }

    public Disposable getIncomePage(OnDBListener<List<InComeSection>> listener) {
        return Observable.create(new ObservableOnSubscribe<List<InComeSection>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<InComeSection>> emitter) throws Exception {
                List<InComeSection> list = new ArrayList<>();
                List<IncomeType> types = mInComeTypeDao.getAllSync();
                for (IncomeType incomeType : types) {
                    InComeSection head = new InComeSection(true, incomeType.getName());
                    list.add(head);
                    List<InCome> inComes = mInComeDao.queryFromIdSync(incomeType.getId());
                    for (InCome inCome : inComes) {
                        InComeSection income = new InComeSection(inCome);
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
}
