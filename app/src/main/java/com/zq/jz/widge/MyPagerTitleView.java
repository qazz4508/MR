package com.zq.jz.widge;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

import com.zq.jz.R;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;

public class MyPagerTitleView extends AppCompatTextView implements IPagerTitleView {
    public MyPagerTitleView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setTextColor(Color.parseColor("#333333"));
        setTextSize(14);
        setGravity(Gravity.CENTER);
    }


    @Override
    public void onSelected(int index, int totalCount) {
        setTextColor(getResources().getColor(R.color.primary));
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        setTextColor(Color.parseColor("#333333"));
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {

    }
}
