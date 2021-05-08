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
        // 设置 是否可以缩放
        mBarChart.setScaleEnabled(false);


        // 获取 x 轴
        XAxis xAxis = mBarChart.getXAxis();
        // 设置 x 轴显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 取消 垂直 网格线
        xAxis.setDrawGridLines(false);
        // 设置 x 轴 坐标旋转角度
        xAxis.setLabelRotationAngle(0f);
        // 设置 x 轴 坐标字体大小
        xAxis.setTextSize(10f);
        // 设置 x 坐标轴 颜色
        xAxis.setAxisLineColor(Color.RED);
        // 设置 x 坐标轴 宽度
        xAxis.setAxisLineWidth(2f);
        // 设置 x轴 的刻度数量
        xAxis.setLabelCount(10);

        // 获取 右边 y 轴
        YAxis mRAxis = mBarChart.getAxisRight();
        // 隐藏 右边 Y 轴
        mRAxis.setEnabled(false);
        // 获取 左边 Y轴
        YAxis mLAxis = mBarChart.getAxisLeft();
        // 取消 左边 Y轴 坐标线
        mLAxis.setDrawAxisLine(false);
        // 取消 横向 网格线
        mLAxis.setDrawGridLines(false);
        // 设置 Y轴 的刻度数量
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
