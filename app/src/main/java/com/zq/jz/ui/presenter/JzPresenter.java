package com.zq.jz.ui.presenter;

import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.BillMultipleItem;
import com.zq.jz.db.JzDB;
import com.zq.jz.ui.contract.JzContract;
import com.zq.jz.ui.model.JzModel;

import java.util.List;

public class JzPresenter extends BasePresenter<JzContract.View> implements JzContract.Presenter {

    private final JzModel mJzModel;

    public JzPresenter() {
        mJzModel = new JzModel();
    }

    @Override
    public void showMonthData() {
        getView().showMonthData(mJzModel.getMonthBarData());
    }

    @Override
    public void showDayData() {
        getView().showDayData(mJzModel.getDayBarData());
    }

    @Override
    public void showBills() {
        List<BillMultipleItem> bills = mJzModel.getBills();
        getView().onGetBillSuccess(bills);
    }
}
