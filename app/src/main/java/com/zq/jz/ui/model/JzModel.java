package com.zq.jz.ui.model;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.zq.jz.db.table.BillBean;
import com.zq.jz.bean.BillMultipleItem;
import com.zq.jz.bean.HomeCardBean;
import com.zq.jz.ui.contract.JzContract;

import java.util.ArrayList;
import java.util.List;

public class JzModel implements JzContract.Model {
    @Override
    public HomeCardBean getMonthBarData() {
        // y 轴数据
        ArrayList<BarEntry> yValues = new ArrayList<>();
        // 2.0 ----x 轴数据
        // ArrayList<String> xValues = new ArrayList<>();

        for (int x = 0; x < 30; x++) {
            // 2.0 ----xValues.add(String.valueOf(i));
            float y = (float) (Math.random() * 30);
            yValues.add(new BarEntry(x, y));
        }

        // y 轴数据集
        BarDataSet barDataSet = new BarDataSet(yValues, "本月支出情况");
        HomeCardBean cardBean = new HomeCardBean();
        cardBean.setBarDataSet(barDataSet);
        cardBean.setPayMoney("5011");
        cardBean.setInComeMoney("1544");
        return cardBean;
    }

    @Override
    public HomeCardBean getDayBarData() {
        // y 轴数据
        ArrayList<BarEntry> yValues = new ArrayList<>();
        // 2.0 ----x 轴数据
        // ArrayList<String> xValues = new ArrayList<>();

        for (int x = 0; x < 10; x++) {
            // 2.0 ----xValues.add(String.valueOf(i));
            float y = (float) (Math.random() * 30);
            yValues.add(new BarEntry(x, y));
        }

        // y 轴数据集
        BarDataSet barDataSet = new BarDataSet(yValues, "本日支出情况");
        HomeCardBean cardBean = new HomeCardBean();
        cardBean.setBarDataSet(barDataSet);
        cardBean.setPayMoney("354");
        cardBean.setInComeMoney("0");
        return cardBean;
    }

    @Override
    public List<BillMultipleItem> getBills() {
        List<BillMultipleItem> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            BillMultipleItem monthItem = new BillMultipleItem(BillMultipleItem.TYPE_MONTH);
            monthItem.setTime(543534);
            list.add(monthItem);
            for (int j = 0; j < 3; j++) {
                BillMultipleItem dayItem = new BillMultipleItem(BillMultipleItem.TYPE_DAY);
                dayItem.setTime(543534);
                list.add(dayItem);
                for (int k = 0; k < 3; k++) {
                    BillMultipleItem billItem = new BillMultipleItem(BillMultipleItem.TYPE_BILL);
                    BillBean billBean = new BillBean();
                    billBean.setTime(23534);
                    billBean.setBill_type(2);
                    billBean.setMoney(30);
                    billBean.setRemark("remark");
                    billBean.setType(0);
                    billItem.setBillBean(billBean);
                    list.add(billItem);
                }
            }
        }
        return list;
    }
}
