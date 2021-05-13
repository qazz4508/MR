package com.zq.jz.ui.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.ui.contract.ChooseAccountContract;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ChooseAccountModel implements ChooseAccountContract.Model {

    private final DbModel mDbModel;

    public ChooseAccountModel() {
        mDbModel = new DbModel();
    }


    @Override
    public Disposable getAccountList(OnGetDataListener<List<MultiItemEntity>> listener) {
        return mDbModel.getAddAccountPage(listener);
    }
}
