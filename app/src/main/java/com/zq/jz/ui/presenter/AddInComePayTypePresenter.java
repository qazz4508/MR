package com.zq.jz.ui.presenter;

import android.text.TextUtils;

import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.InComeSection;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.db.table.InComePay;
import com.zq.jz.db.table.UserInComePayType;
import com.zq.jz.ui.contract.AddInComeTypeContract;
import com.zq.jz.ui.model.DbModel;
import com.zq.jz.ui.model.UserInfoModel;
import com.zq.jz.util.LogUtil;

import java.util.List;

public class AddInComePayTypePresenter extends BasePresenter<AddInComeTypeContract.View> implements AddInComeTypeContract.Presenter {

    private final DbModel mDbModel;
    private final UserInfoModel mUserInfoModel;

    public AddInComePayTypePresenter() {
        mDbModel = new DbModel();
        mUserInfoModel = new UserInfoModel();
    }

    @Override
    public void showTypes() {
        mDbModel.getIncomePage(2, new OnGetDataListener<List<InComeSection>>() {
            @Override
            public void onSuccess(List<InComeSection> inComeSections) {
                getView().onGetTypeSuccess(inComeSections);
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

    @Override
    public void insert(InComePay inComePay) {
        UserInComePayType userInComePayType = new UserInComePayType();
        userInComePayType.setBillTypeId(2);
        userInComePayType.setTypeId(inComePay.getId());
        userInComePayType.setName(inComePay.getName());
        if (TextUtils.isEmpty(getView().getAnotherName())) {
            userInComePayType.setAnotherName(inComePay.getName());
        } else {
            userInComePayType.setAnotherName(getView().getAnotherName());
        }
        mUserInfoModel.insertUserType(userInComePayType, new OnGetDataListener<String>() {
            @Override
            public void onSuccess(String aLong) {
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
