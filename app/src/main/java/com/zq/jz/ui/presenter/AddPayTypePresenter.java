package com.zq.jz.ui.presenter;

import android.text.TextUtils;

import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.InComeSection;
import com.zq.jz.db.listener.OnDBListener;
import com.zq.jz.db.table.InComePay;
import com.zq.jz.db.table.UserInComePayType;
import com.zq.jz.ui.contract.AddInComeTypeContract;
import com.zq.jz.ui.model.DbModel;
import com.zq.jz.util.LogUtil;

import java.util.List;

public class AddPayTypePresenter extends BasePresenter<AddInComeTypeContract.View> implements AddInComeTypeContract.Presenter {

    private final DbModel mDbModel;

    public AddPayTypePresenter() {
        mDbModel = new DbModel();
    }

    @Override
    public void showTypes() {
        mDbModel.getIncomePage(1, new OnDBListener<List<InComeSection>>() {
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
        userInComePayType.setBillTypeId(1);
        userInComePayType.setTypeId(inComePay.getId());
        userInComePayType.setName(inComePay.getName());
        if (TextUtils.isEmpty(getView().getAnotherName())) {
            userInComePayType.setAnotherName(inComePay.getName());
        } else {
            userInComePayType.setAnotherName(getView().getAnotherName());
        }
        mDbModel.insertUserType(userInComePayType, new OnDBListener() {
            @Override
            public void onSuccess(Object o) {
                getView().onInsertSuccess();
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
