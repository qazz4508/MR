package com.zq.jz.ui.presenter;

import android.util.Log;

import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.UserIncomePayTypeMultipleItem;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.ui.contract.UserIncomePayTypeContract;
import com.zq.jz.ui.model.UserIncomePayTypeModel;
import com.zq.jz.ui.model.UserInfoModel;
import com.zq.jz.util.LogUtil;

import java.util.List;

public class UserIncomePayTypePresenter extends BasePresenter<UserIncomePayTypeContract.View> implements UserIncomePayTypeContract.Presenter {

    private final UserIncomePayTypeModel mModel;

    public UserIncomePayTypePresenter() {
        mModel = new UserIncomePayTypeModel();
    }

    @Override
    public void getType() {
        mModel.getUserType(getView().getPageType(), new OnGetDataListener<List<UserIncomePayTypeMultipleItem>>() {
            @Override
            public void onSuccess(List<UserIncomePayTypeMultipleItem> list) {
                getView().onGetTypeSuccess(list);
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
