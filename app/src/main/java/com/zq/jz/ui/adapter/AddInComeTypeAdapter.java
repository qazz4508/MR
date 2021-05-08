package com.zq.jz.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zq.jz.R;
import com.zq.jz.bean.InComeSection;
import com.zq.jz.db.table.InComePay;

import java.util.List;

public class AddInComeTypeAdapter extends BaseSectionQuickAdapter<InComeSection, BaseViewHolder> {

    private int mSelectPosition;
    private OnTypeClickListener mOnTypeClickListener;

    public AddInComeTypeAdapter(List<InComeSection> data) {
        super(R.layout.item_type, R.layout.add_income_type_head, data);
        mSelectPosition = 1;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, InComeSection item) {
        helper.setText(R.id.tv_type, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, InComeSection item) {
        helper.setText(R.id.tv_name, item.t.getName());
        if (mSelectPosition == helper.getAdapterPosition()) {
            helper.setVisible(R.id.iv_select, true);
        } else {
            helper.setVisible(R.id.iv_select, false);
        }

        helper.itemView.setTag(helper.getAdapterPosition());
        helper.itemView.setOnClickListener(mOnClickListener);
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mSelectPosition = (int) v.getTag();
            if(mOnTypeClickListener!=null){
                mOnTypeClickListener.onTypeClick(mSelectPosition,getData().get(mSelectPosition).t);
            }
            notifyDataSetChanged();
        }
    };

    public void setOnTypeClickListener(OnTypeClickListener onTypeClickListener) {
        mOnTypeClickListener = onTypeClickListener;
    }

    public void setData(List<InComeSection> list){
        setNewData(list);
        if(mOnTypeClickListener!=null){
            mOnTypeClickListener.onTypeClick(mSelectPosition,getData().get(mSelectPosition).t);
        }
    }

    public interface OnTypeClickListener{
        void onTypeClick(int position, InComePay inComePay);
    }
}
