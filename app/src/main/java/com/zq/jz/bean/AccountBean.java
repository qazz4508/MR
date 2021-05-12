package com.zq.jz.bean;

import com.zq.jz.db.table.UserAccount;

public class AccountBean {
    private UserAccount mUserAccount;

    public UserAccount getUserAccount() {
        return mUserAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        mUserAccount = userAccount;
    }
}
