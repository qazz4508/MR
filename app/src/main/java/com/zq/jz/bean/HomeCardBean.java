package com.zq.jz.bean;

import com.github.mikephil.charting.data.BarDataSet;

public class HomeCardBean {
    private BarDataSet mBarDataSet;
    private String mPayMoney;
    private String mInComeMoney;

    public BarDataSet getBarDataSet() {
        return mBarDataSet;
    }

    public void setBarDataSet(BarDataSet barDataSet) {
        mBarDataSet = barDataSet;
    }

    public String getPayMoney() {
        return mPayMoney;
    }

    public void setPayMoney(String payMoney) {
        mPayMoney = payMoney;
    }

    public String getInComeMoney() {
        return mInComeMoney;
    }

    public void setInComeMoney(String inComeMoney) {
        mInComeMoney = inComeMoney;
    }
}
