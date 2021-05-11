package com.zq.jz.ui.contract;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.base.BaseView;
import com.zq.jz.bean.AccountExpandItem;

import java.util.List;

public interface AddAccountContract {
    interface Model{
        List<MultiItemEntity> getAccountList();
    }

    interface View extends BaseView{
        void onGetAccountListSuccess(List<MultiItemEntity> list);
    }

    interface Presenter{
        void getAccountList();
    }
}
