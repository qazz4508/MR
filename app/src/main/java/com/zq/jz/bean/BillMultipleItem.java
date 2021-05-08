package com.zq.jz.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.db.table.BillBean;

public class BillMultipleItem implements MultiItemEntity {
    public static final int TYPE_MONTH = 0;
    public static final int TYPE_DAY = 1;
    public static final int TYPE_BILL = 2;

    private int itemType;
    private BillBean billBean;
    private long time;

    public BillMultipleItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public BillBean getBillBean() {
        return billBean;
    }

    public void setBillBean(BillBean billBean) {
        this.billBean = billBean;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
