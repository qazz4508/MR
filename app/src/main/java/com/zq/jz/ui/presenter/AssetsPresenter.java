package com.zq.jz.ui.presenter;

import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.AccountBean;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.ui.contract.AssetsContract;
import com.zq.jz.ui.model.UserAccountModel;

import java.util.List;

public class AssetsPresenter extends BasePresenter<AssetsContract.View> implements AssetsContract.Presenter{

    private final UserAccountModel mUserAccountModel;

    public AssetsPresenter() {
        mUserAccountModel = new UserAccountModel();
    }

    @Override
    public void getUserAccount() {
        mUserAccountModel.getUserAccount(new OnGetDataListener<List<AccountBean>>() {
            @Override
            public void onSuccess(List<AccountBean> list) {
                getView().onGetUserAccountSuccess(list);
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
