package com.zq.jz.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.db.table.IncomePayType;
import com.zq.jz.db.table.UserInComePayType;

public class UserIncomePayTypeMultipleItem implements MultiItemEntity {
    public static final int TYPE_TYPE = 0;
    public static final int TYPE_ADD = 1;

    private int itemType;
    private UserInComePayType mIncomePayType;

    public UserIncomePayTypeMultipleItem(int itemType) {
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public UserInComePayType getIncomePayType() {
        return mIncomePayType;
    }

    public void setIncomePayType(UserInComePayType incomePayType) {
        mIncomePayType = incomePayType;
    }
}
