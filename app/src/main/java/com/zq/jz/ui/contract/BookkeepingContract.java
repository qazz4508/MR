package com.zq.jz.ui.contract;

import com.zq.jz.base.BaseView;
import com.zq.jz.db.table.UserAccount;

public interface BookkeepingContract {
    interface View extends BaseView {
        void onGetSelectAccountSuccess(UserAccount userAccount);
    }

    interface Presenter {
        void getSelectAccount();
        void updateUserConfigSelectAccount(int id);
    }
}
