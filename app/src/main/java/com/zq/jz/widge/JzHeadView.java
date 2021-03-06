package com.zq.jz.widge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.zq.jz.R;
import com.zq.jz.bean.HomeCardBean;
import com.zq.jz.util.SpDataManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JzHeadView extends FrameLayout {

    @BindView(R.id.bar_chart)
    BarChart mBarChart;
    @BindView(R.id.tv_pay)
    TextView mTvPay;
    @BindView(R.id.tv_in)
    TextView mTvIn;

    private BarData mBarData;

    private onHeadListener mOnHeadListener;

    public JzHeadView(@NonNull Context context) {
        this(context, null);
    }

    public JzHeadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.home_head_layout, this);
        ButterKnife.bind(this, view);
        initBar();
    }

    @OnClick(R.id.iv_switch)
    public void onSwitchClick() {
        int homeCardType = SpDataManager.getHomeCardType();
        switch (homeCardType) {
            case 0:
                if(mOnHeadListener!=null){
                    mOnHeadListener.onShowDayClick();
                    SpDataManager.setHomeCardType(1);
                }
                break;
            case 1:
                if(mOnHeadListener!=null){
                    mOnHeadListener.onShowMonthClick();
                    SpDataManager.setHomeCardType(0);
                }
                break;
        }
    }

    private void initBar() {
        Description description = new Description();
        description.setXOffset(0f);
        description.setYOffset(0f);
        description.setText("");
        mBarChart.setDescription(description);
        // ?????? ??????????????????
        mBarChart.setScaleEnabled(false);


        // ?????? x ???
        XAxis xAxis = mBarChart.getXAxis();
        // ?????? x ???????????????
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // ?????? ?????? ?????????
        xAxis.setDrawGridLines(false);
        // ?????? x ??? ??????????????????
        xAxis.setLabelRotationAngle(0f);
        // ?????? x ??? ??????????????????
        xAxis.setTextSize(10f);
        // ?????? x ????????? ??????
        xAxis.setAxisLineColor(Color.RED);
        // ?????? x ????????? ??????
        xAxis.setAxisLineWidth(2f);
        // ?????? x??? ???????????????
        xAxis.setLabelCount(10);

        // ?????? ?????? y ???
        YAxis mRAxis = mBarChart.getAxisRight();
        // ?????? ?????? Y ???
        mRAxis.setEnabled(false);
        // ?????? ?????? Y???
        YAxis mLAxis = mBarChart.getAxisLeft();
        // ?????? ?????? Y??? ?????????
        mLAxis.setDrawAxisLine(false);
        // ?????? ?????? ?????????
        mLAxis.setDrawGridLines(false);
        // ?????? Y??? ???????????????
        mLAxis.setLabelCount(6);

        mBarChart.setBorderColor(Color.RED);
    }

    public void showMonthData(HomeCardBean dataSet) {
        mBarChart.clear();
        mBarData = new BarData(dataSet.getBarDataSet());
        mBarChart.setData(mBarData);

        mTvPay.setText(dataSet.getPayMoney());
        mTvIn.setText(dataSet.getInComeMoney());
    }

    public void showDayData(HomeCardBean dataSet) {
        mBarChart.clear();
        mBarData = new BarData(dataSet.getBarDataSet());
        mBarChart.setData(mBarData);

        mTvPay.setText(dataSet.getPayMoney());
        mTvIn.setText(dataSet.getInComeMoney());
    }

    public void setOnHeadListener(onHeadListener onHeadListener) {
        mOnHeadListener = onHeadListener;
    }

    public interface onHeadListener {
        void onShowDayClick();
        void onShowMonthClick();
    }
}
