package com.zq.jz.ui.contract;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.zq.jz.base.BaseView;
import com.zq.jz.bean.BillMultipleItem;
import com.zq.jz.bean.HomeCardBean;

import java.util.List;

public interface JzContract {
    interface Model {
        HomeCardBean getMonthBarData();

        HomeCardBean getDayBarData();

        List<BillMultipleItem> getBills();
    }

    interface View extends BaseView {
        void showMonthData(HomeCardBean dataSet);

        void showDayData(HomeCardBean dataSet);

        void onGetBillSuccess(List<BillMultipleItem> list);
    }

    interface Presenter {
        void showMonthData();

        void showDayData();

        void showBills();
    }
}
