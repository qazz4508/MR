package com.zq.jz.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.db.table.AccountType;

import java.util.List;

public class AccountExpandItem extends AbstractExpandableItem<AccountChildItem> implements MultiItemEntity {

    public static final int TYPE_L0 = 0;
    public static final int TYPE_L1 = 1;
    private AccountType mAccountType;

    public AccountExpandItem(AccountType accountType) {
        mAccountType = accountType;
    }

    public String getSubItemNameString() {
        List<AccountChildItem> subItems = getSubItems();
        StringBuilder sb = new StringBuilder();
        for (AccountChildItem subItem : subItems) {
            sb.append(subItem.getAccount().getName());
            sb.append("  ");
        }
        return sb.toString();
    }

    public AccountType getAccountType() {
        return mAccountType;
    }

    public void setAccountType(AccountType accountType) {
        mAccountType = accountType;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return TYPE_L0;
    }
}
