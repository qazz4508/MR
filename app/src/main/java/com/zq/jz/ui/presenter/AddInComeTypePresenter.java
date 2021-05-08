package com.zq.jz.ui.presenter;

import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.InComeSection;
import com.zq.jz.db.listener.OnDBListener;
import com.zq.jz.ui.contract.AddInComeTypeContract;
import com.zq.jz.ui.model.DbModel;
import com.zq.jz.util.LogUtil;

import java.util.List;

public class AddInComeTypePresenter extends BasePresenter<AddInComeTypeContract.View> implements AddInComeTypeContract.Presenter {

    private final DbModel mDbModel;

    public AddInComeTypePresenter() {
        mDbModel = new DbModel();
    }

    @Override
    public void showTypes() {
        mDbModel.getIncomePage(new OnDBListener<List<InComeSection>>() {
            @Override
            public void onSuccess(List<InComeSection> inComeSections) {
                getView().onGetTypeSuccess(inComeSections);
            }

            @Override
            public void onError(String msg) {
                LogUtil.log(msg);
            }

            @Override
            public void onEmpty() {

            }
        });
    }
}
