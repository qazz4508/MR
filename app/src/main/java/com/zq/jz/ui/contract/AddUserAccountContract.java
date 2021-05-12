package com.zq.jz.ui.contract;

import com.zq.jz.base.BaseView;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.db.table.Account;
import com.zq.jz.db.table.AccountType;
import com.zq.jz.db.table.UserAccount;

import io.reactivex.disposables.Disposable;

public interface AddUserAccountContract {
    interface Model {
        Disposable insert(UserAccount userAccount, OnGetDataListener<String> listener);
    }

    interface View extends BaseView {
        void onInsertSuccess();

        String getInputName();

        String getInputOver();

        String getInputRemark();

        AccountType getAccountType();

        Account getAccount();
    }

    interface Presenter {
        void insert();
    }
}
