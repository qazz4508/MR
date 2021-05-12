package com.zq.jz.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.db.table.Account;

import static com.zq.jz.bean.AccountExpandItem.TYPE_L1;

public class AccountChildItem implements MultiItemEntity {
    private Account mAccount;

    public AccountChildItem(Account account) {
        mAccount = account;
    }

    public Account getAccount() {
        return mAccount;
    }

    public void setAccount(Account account) {
        mAccount = account;
    }

    @Override
    public int getItemType() {
        return TYPE_L1;
    }

    @Override
    public String toString() {
        return "AccountChildItem{" +
                "mAccount=" + mAccount +
                '}';
    }
}
