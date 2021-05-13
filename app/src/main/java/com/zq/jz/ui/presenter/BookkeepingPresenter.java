package com.zq.jz.ui.presenter;

import com.zq.jz.base.BasePresenter;
import com.zq.jz.db.listener.OnGetDataListener;
import com.zq.jz.db.table.UserAccount;
import com.zq.jz.ui.contract.BookkeepingContract;
import com.zq.jz.ui.model.UserConfigModel;
import com.zq.jz.util.LogUtil;

public class BookkeepingPresenter extends BasePresenter<BookkeepingContract.View> implements BookkeepingContract.Presenter {

    private final UserConfigModel mConfigModel;

    public BookkeepingPresenter() {
        mConfigModel = new UserConfigModel();
    }

    @Override
    public void getSelectAccount() {
        mConfigModel.getSelectAccount(new OnGetDataListener<UserAccount>() {
            @Override
            public void onSuccess(UserAccount userAccount) {
                getView().onGetSelectAccountSuccess(userAccount);
            }

            @Override
            public void onError(String msg) {
                getView().showToast(msg);
            }

            @Override
            public void onEmpty() {

            }
        });
    }

    @Override
    public void updateUserConfigSelectAccount(int id) {
        mConfigModel.updateSelectAccount(id);
    }


}
