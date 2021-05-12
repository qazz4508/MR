package com.zq.jz.ui.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.bean.AccountExpandItem;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.ui.contract.AddAccountContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class AddAccountModel implements AddAccountContract.Model {

    private final DbModel mDbModel;

    public AddAccountModel() {
        mDbModel = new DbModel();
    }


    @Override
    public Disposable getAccountList(OnGetDataListener<List<MultiItemEntity>> listener) {
        return mDbModel.getAddAccountPage(listener);
    }
}
