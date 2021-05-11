package com.zq.jz.ui.presenter;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.ui.contract.AddAccountContract;
import com.zq.jz.ui.model.AddAccountModel;

import java.util.List;

public class AddAccountPresenter extends BasePresenter<AddAccountContract.View> implements AddAccountContract.Presenter {

    private final AddAccountModel mModel;

    public AddAccountPresenter() {
        mModel = new AddAccountModel();
    }

    @Override
    public void getAccountList() {
        List<MultiItemEntity> accountList = mModel.getAccountList();
        getView().onGetAccountListSuccess(accountList);
    }
}
