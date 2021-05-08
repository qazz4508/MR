package com.zq.jz.ui.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zq.jz.R;
import com.zq.jz.bean.BillMultipleItem;


import java.util.List;

public class JzAdapter extends BaseMultiItemQuickAdapter<BillMultipleItem, BaseViewHolder> {


    public JzAdapter(List<BillMultipleItem> data) {
        super(data);

        addItemType(BillMultipleItem.TYPE_MONTH, R.layout.item_jz_month);
        addItemType(BillMultipleItem.TYPE_DAY, R.layout.item_jz_month);
        addItemType(BillMultipleItem.TYPE_BILL, R.layout.item_jz);
    }

    @Override
    protected void convert(BaseViewHolder helper, BillMultipleItem item) {
        switch (helper.getItemViewType()) {
            case BillMultipleItem.TYPE_MONTH:
                helper.setText(R.id.tv_time, item.getTime() + "");
                break;
            case BillMultipleItem.TYPE_DAY:
                helper.setText(R.id.tv_time, item.getTime() + "");
                break;
            case BillMultipleItem.TYPE_BILL:
                helper.setText(R.id.tv_type, item.getBillBean().getType()+"--");
                if (TextUtils.isEmpty(item.getBillBean().getRemark())) {
                    helper.setGone(R.id.tv_remark, false);
                } else {
                    helper.setGone(R.id.tv_remark, true);
                    helper.setText(R.id.tv_remark, item.getBillBean().getRemark());
                }
                helper.setText(R.id.tv_money, item.getBillBean().getMoney() + "");
                break;
        }
    }
}
