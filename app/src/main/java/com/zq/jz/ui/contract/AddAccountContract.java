package com.zq.jz.ui.contract;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.base.BaseView;
import com.zq.jz.bean.AccountExpandItem;
import com.zq.jz.db.listener.OnGetDataListener;

import java.util.List;

import io.reactivex.disposables.Disposable;

public interface AddAccountContract {
    interface Model {
        Disposable getAccountList(OnGetDataListener<List<MultiItemEntity>> listener);
    }

    interface View extends BaseView {
        void onGetAccountListSuccess(List<MultiItemEntity> list);
    }

    interface Presenter {
        void getAccountList();
    }
}
