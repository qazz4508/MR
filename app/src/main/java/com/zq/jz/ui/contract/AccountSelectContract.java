package com.zq.jz.ui.contract;

import com.zq.jz.base.BaseView;
import com.zq.jz.bean.AccountBean;

import java.util.List;

public interface AccountSelectContract {
    interface View extends BaseView{
        void onGetAccountSuccess(List<AccountBean> list);
    }

    interface Presenter{
        void getAccountList();
    }
}
