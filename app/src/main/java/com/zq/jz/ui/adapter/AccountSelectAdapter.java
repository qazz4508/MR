package com.zq.jz.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zq.jz.R;
import com.zq.jz.bean.AccountBean;

import java.util.List;

public class AccountSelectAdapter extends BaseQuickAdapter<AccountBean, BaseViewHolder> {
    public AccountSelectAdapter(@Nullable List<AccountBean> data) {
        super(R.layout.item_account_select, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountBean item) {
        helper.setText(R.id.tv_name,item.getUserAccount().getName());
        helper.setText(R.id.tv_over,item.getUserAccount().getOver()+"");
    }
}
