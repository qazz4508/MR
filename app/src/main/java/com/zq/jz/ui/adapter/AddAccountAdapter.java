package com.zq.jz.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zq.jz.R;
import com.zq.jz.bean.AccountChildItem;
import com.zq.jz.bean.AccountExpandItem;

import java.util.List;

public class AddAccountAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public AddAccountAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(AccountExpandItem.TYPE_L0, R.layout.item_add_accout_l0);
        addItemType(AccountExpandItem.TYPE_L1, R.layout.item_add_accout_l0);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case AccountExpandItem.TYPE_L0:
                AccountExpandItem accountExpandItem = (AccountExpandItem) item;
                helper.setText(R.id.tv_title, accountExpandItem.getAccountType().getName());
                if(accountExpandItem.hasSubItem()){
                    helper.setText(R.id.tv_sub, accountExpandItem.getSubItemNameString());
                    helper.getView(R.id.iv_arrow).setVisibility(View.VISIBLE);
                }else {
                    helper.getView(R.id.tv_sub).setVisibility(View.GONE);
                    helper.getView(R.id.iv_arrow).setVisibility(View.GONE);
                }
                break;
            case AccountExpandItem.TYPE_L1:
                AccountChildItem accountChildItem = (AccountChildItem) item;
                helper.setText(R.id.tv_title, accountChildItem.getAccount().getName());
                helper.getView(R.id.tv_sub).setVisibility(View.GONE);
                helper.getView(R.id.iv_arrow).setVisibility(View.GONE);
                break;
        }
    }
}
