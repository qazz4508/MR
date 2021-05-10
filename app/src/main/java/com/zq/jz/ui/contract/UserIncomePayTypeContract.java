package com.zq.jz.ui.contract;

import com.zq.jz.base.BaseView;
import com.zq.jz.bean.UserIncomePayTypeMultipleItem;
import com.zq.jz.db.listener.OnGetDataListener;

import java.util.List;

public interface UserIncomePayTypeContract {
    interface Model{
        void getUserType(int type, OnGetDataListener<List<UserIncomePayTypeMultipleItem>> listener);
    }

    interface View extends BaseView {
        void onGetTypeSuccess(List<UserIncomePayTypeMultipleItem> list);
        int getPageType();
    }

    interface Presenter{
        void getType();
    }
}
