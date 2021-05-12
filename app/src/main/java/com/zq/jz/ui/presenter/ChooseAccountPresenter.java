package com.zq.jz.ui.presenter;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.ui.contract.ChooseAccountContract;
import com.zq.jz.ui.model.AddAccountModel;
import com.zq.jz.util.LogUtil;

import java.util.List;

public class ChooseAccountPresenter extends BasePresenter<ChooseAccountContract.View> implements ChooseAccountContract.Presenter {

    private final AddAccountModel mModel;

    public ChooseAccountPresenter() {
        mModel = new AddAccountModel();
    }

    @Override
    public void getAccountList() {
         mModel.getAccountList(new OnGetDataListener<List<MultiItemEntity>>() {
             @Override
             public void onSuccess(List<MultiItemEntity> multiItemEntities) {
                 getView().onGetAccountListSuccess(multiItemEntities);
                 LogUtil.log("onSuccess");

             }

             @Override
             public void onError(String msg) {
                 LogUtil.log(msg);
             }

             @Override
             public void onEmpty() {

             }
         });
    }
}
