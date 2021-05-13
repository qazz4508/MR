package com.zq.jz.ui.presenter;

import android.text.TextUtils;

import com.zq.jz.base.BasePresenter;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.db.table.Account;
import com.zq.jz.db.table.AccountType;
import com.zq.jz.db.table.UserAccount;
import com.zq.jz.ui.contract.AddUserAccountContract;
import com.zq.jz.ui.model.UserAccountModel;
import com.zq.jz.util.LogUtil;

public class AddUserAccountPresenter extends BasePresenter<AddUserAccountContract.View> implements AddUserAccountContract.Presenter {

    private final UserAccountModel mUserAccountModel;

    public AddUserAccountPresenter() {
        mUserAccountModel = new UserAccountModel();
    }

    @Override
    public void insert() {
        if(TextUtils.isEmpty(getView().getInputName())){
            getView().showToast("请输入账户名称");
            return;
        }
        if(TextUtils.isEmpty(getView().getInputOver())){
            getView().showToast("请输入账户余额");
            return;
        }
        Account account = getView().getAccount();
        AccountType accountType = getView().getAccountType();
        UserAccount userAccount = new UserAccount();
        if (account == null) {
            userAccount.setAccountTypePid(accountType.getId());
        } else {
            userAccount.setAccountTypePid(account.getTypeId());
            userAccount.setAccountTypeCid(account.getId());
        }

        userAccount.setName(getView().getInputName());
        userAccount.setOver(Double.parseDouble(getView().getInputOver()));
        userAccount.setRemark(getView().getInputRemark());
        mUserAccountModel.insert(userAccount, new OnGetDataListener<String>() {
            @Override
            public void onSuccess(String s) {
                getView().onInsertSuccess();
            }

            @Override
            public void onError(String msg) {
            }

            @Override
            public void onEmpty() {

            }
        });
    }
}
