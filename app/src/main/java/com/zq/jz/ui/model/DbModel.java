package com.zq.jz.ui.model;

import android.annotation.SuppressLint;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.MyApplication;
import com.zq.jz.bean.AccountChildItem;
import com.zq.jz.bean.AccountExpandItem;
import com.zq.jz.bean.InComeSection;
import com.zq.jz.db.dao.AccountDao;
import com.zq.jz.db.dao.AccountTypeDao;
import com.zq.jz.db.dao.InComePayDao;
import com.zq.jz.db.dao.InComePayTypeDao;
import com.zq.jz.db.table.Account;
import com.zq.jz.db.table.AccountType;
import com.zq.jz.db.table.BillType;
import com.zq.jz.db.dao.BillTypeDao;
import com.zq.jz.db.JzDB;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.db.table.InComePay;
import com.zq.jz.db.table.IncomePayType;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DbModel {

    private final JzDB mJzDB;
    private final BillTypeDao mBillTypeDao;
    private final InComePayTypeDao mInComeTypeDao;
    private final InComePayDao mInComeDao;
    private final AccountDao mAccountDao;
    private final AccountTypeDao mAccountTypeDao;

    public DbModel() {
        mJzDB = JzDB.getInstance(MyApplication.getAppContext());
        mBillTypeDao = mJzDB.getBillTypeDao();
        mInComeTypeDao = mJzDB.getInComePayTypeDao();
        mInComeDao = mJzDB.getInComePayDao();
        mAccountDao = mJzDB.getAccountDao();
        mAccountTypeDao = mJzDB.getAccountTypeDao();
    }

    /**
     * 获取添加收入支出类型界面数据
     *
     * @param type 1支出2收入
     */
    public Disposable getIncomePage(int type, OnGetDataListener<List<InComeSection>> listener) {
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

    @SuppressLint("all")
    public Disposable getAddAccountPage(OnGetDataListener<List<MultiItemEntity>> listener) {
        Disposable subscribe = Observable.create(new ObservableOnSubscribe<List<AccountType>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<AccountType>> emitter) throws Exception {
                List<AccountType> all = mAccountTypeDao.getAll();
                emitter.onNext(all);
            }
        }).map(new Function<List<AccountType>, List<MultiItemEntity>>() {
            @Override
            public List<MultiItemEntity> apply(@NonNull List<AccountType> accountTypes) throws Exception {
                List<MultiItemEntity> list = new ArrayList<>();
                for (AccountType accountType : accountTypes) {
                    AccountExpandItem item = new AccountExpandItem(accountType);
                    List<Account> accountList = mAccountDao.getFormType(item.getAccountType().getId());
                    for (Account account : accountList) {
                        AccountChildItem accountChildItem = new AccountChildItem(account);
                        item.addSubItem(accountChildItem);
                    }
                    list.add(item);
                }
                return list;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MultiItemEntity>>() {
                    @Override
                    public void accept(List<MultiItemEntity> multiItemEntities) throws Exception {
                        listener.onSuccess(multiItemEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
        return subscribe;
    }
}
