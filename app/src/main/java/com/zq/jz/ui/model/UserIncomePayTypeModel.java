package com.zq.jz.ui.model;

import com.zq.jz.bean.UserIncomePayTypeMultipleItem;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.db.table.UserInComePayType;
import com.zq.jz.ui.contract.UserIncomePayTypeContract;
import com.zq.jz.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class UserIncomePayTypeModel implements UserIncomePayTypeContract.Model {

    private final UserInfoModel mUserInfoModel;

    public UserIncomePayTypeModel() {
        mUserInfoModel = new UserInfoModel();
    }

    @Override
    public void getUserType(int type, OnGetDataListener<List<UserIncomePayTypeMultipleItem>> listener) {
        mUserInfoModel.getUserType(type, new OnGetDataListener<List<UserInComePayType>>() {
            @Override
            public void onSuccess(List<UserInComePayType> userInComePayTypes) {
                List<UserIncomePayTypeMultipleItem> list = new ArrayList<>();
                for (UserInComePayType userInComePayType : userInComePayTypes) {
                    UserIncomePayTypeMultipleItem item = new UserIncomePayTypeMultipleItem(UserIncomePayTypeMultipleItem.TYPE_TYPE);
                    item.setIncomePayType(userInComePayType);
                    list.add(item);
                }
                UserIncomePayTypeMultipleItem item = new UserIncomePayTypeMultipleItem(UserIncomePayTypeMultipleItem.TYPE_ADD);
                list.add(item);
                listener.onSuccess(list);
            }

            @Override
            public void onError(String msg) {
                listener.onError(msg);
            }

            @Override
            public void onEmpty() {

            }
        });
    }
}
