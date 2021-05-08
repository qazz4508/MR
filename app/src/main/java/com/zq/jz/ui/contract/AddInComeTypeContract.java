package com.zq.jz.ui.contract;

import com.zq.jz.base.BaseView;
import com.zq.jz.bean.BillMultipleItem;
import com.zq.jz.bean.HomeCardBean;
import com.zq.jz.bean.InComeSection;

import java.util.List;

public interface AddInComeTypeContract {
    interface Model {

    }

    interface View extends BaseView {
        void onGetTypeSuccess(List<InComeSection> list);
    }

    interface Presenter {
        void showTypes();
    }
}
