package com.zq.jz.ui.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zq.jz.R;
import com.zq.jz.bean.UserIncomePayTypeMultipleItem;
import com.zq.jz.db.table.UserInComePayType;

import java.util.List;

public class UserIncomePayTypeAdapter extends BaseMultiItemQuickAdapter<UserIncomePayTypeMultipleItem, BaseViewHolder> {

    private int mSelectPosition;

    public UserIncomePayTypeAdapter(List<UserIncomePayTypeMultipleItem> data) {
        super(data);

        mSelectPosition = 0;

        addItemType(UserIncomePayTypeMultipleItem.TYPE_TYPE, R.layout.item_type);
        addItemType(UserIncomePayTypeMultipleItem.TYPE_ADD, R.layout.item_type);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserIncomePayTypeMultipleItem item) {
        int itemViewType = helper.getItemViewType();
        switch (itemViewType) {
            case UserIncomePayTypeMultipleItem.TYPE_TYPE:
                if (mSelectPosition == helper.getAdapterPosition()) {
                    helper.getView(R.id.iv_select).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.iv_select).setVisibility(View.INVISIBLE);
                }
                if (TextUtils.isEmpty(item.getIncomePayType().getAnotherName())) {
                    helper.setText(R.id.tv_name, item.getIncomePayType().getName());
                } else {
                    helper.setText(R.id.tv_name, item.getIncomePayType().getAnotherName());
                }
                break;
            case UserIncomePayTypeMultipleItem.TYPE_ADD:
                helper.setText(R.id.tv_name, "添加");
                break;
        }
    }

    public void setSelectPosition(int selectPosition) {
        mSelectPosition = selectPosition;
        notifyDataSetChanged();
    }

    public int getSelectPosition() {
        return mSelectPosition;
    }

    public UserInComePayType getSelectItem() {
        if (getData().size() > 0) {
            return getData().get(getSelectPosition()).getIncomePayType();
        }
        return null;
    }
}
