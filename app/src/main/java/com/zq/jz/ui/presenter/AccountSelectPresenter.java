package com.zq.jz.ui.presenter;

import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.AccountBean;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.ui.contract.AccountSelectContract;
import com.zq.jz.ui.model.UserAccountModel;

import java.util.List;

public class AccountSelectPresenter extends BasePresenter<AccountSelectContract.View> implements AccountSelectContract.Presenter{

    private final UserAccountModel mUserAccountModel;

    public AccountSelectPresenter() {
        mUserAccountModel = new UserAccountModel();
    }

    @Override
    public void getAccountList() {
        mUserAccountModel.getUserAccount(new OnGetDataListener<List<AccountBean>>() {
            @Override
            public void onSuccess(List<AccountBean> list) {
                getView().onGetAccountSuccess(list);
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
