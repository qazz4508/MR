package com.zq.jz.ui.contract;

import com.zq.jz.base.BaseView;
import com.zq.jz.bean.AccountBean;

import java.util.List;

public interface AssetsContract {
    interface View extends BaseView{
        void onGetUserAccountSuccess(List<AccountBean> list);
    }

    interface Presenter{
        void getUserAccount();
    }
}
