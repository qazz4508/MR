package com.zq.jz.bean.event;

import com.zq.jz.db.table.UserInComePayType;

public class EventUserIncomePaySelect {
    private UserInComePayType mUserInComePayType;

    public EventUserIncomePaySelect(UserInComePayType userInComePayType) {
        mUserInComePayType = userInComePayType;
    }

    public UserInComePayType getUserInComePayType() {
        return mUserInComePayType;
    }

    public void setUserInComePayType(UserInComePayType userInComePayType) {
        mUserInComePayType = userInComePayType;
    }
}
