package com.zq.jz.ui.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.necer.calendar.BaseCalendar;
import com.necer.calendar.MonthCalendar;
import com.necer.enumeration.DateChangeBehavior;
import com.necer.listener.OnCalendarChangedListener;
import com.zq.jz.R;
import com.zq.jz.base.BaseMvpDialogFragment;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.util.DateUtil;
import com.zq.jz.util.LogUtil;

import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CalendarDialog extends BaseMvpDialogFragment {
    @BindView(R.id.mc)
    MonthCalendar mMonthCalendar;
    @BindView(R.id.tv_date)
    TextView mTvDate;

    private OnCalendarListener mOnCalendarListener;
    private Calendar mCalendar;

    public static void show(FragmentManager fragmentManager, Calendar calendar, OnCalendarListener onCalendarListener) {
        CalendarDialog calendarDialog = new CalendarDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cl", calendar);
        calendarDialog.setArguments(bundle);
        calendarDialog.setOnCalendarListener(onCalendarListener);
        calendarDialog.show(fragmentManager, CalendarDialog.class.getName());
    }


    @Override
    protected void createPresenter(List<BasePresenter> mPresenters) {

    }

    @Override
    protected void initData() {
        mCalendar = (Calendar) getArguments().getSerializable("cl");
    }

    @Override
    protected void initView() {
        mMonthCalendar.setOnCalendarChangedListener(null);
        mMonthCalendar.setInitializeDate(DateUtil.getYYYY_MM_DD(mCalendar));
        mMonthCalendar.setOnCalendarChangedListener(null);
    }

    @Override
    protected void initListener() {
        mMonthCalendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate, DateChangeBehavior dateChangeBehavior) {
                mTvDate.setText(year + "-" + month + "-" + localDate.getDayOfMonth());
                if (dateChangeBehavior == DateChangeBehavior.CLICK) {
                    if (mOnCalendarListener != null) {
                        mOnCalendarListener.onDateSelect(DateUtil.getCalendar(year, month-1, localDate.getDayOfMonth()));
                        dismissAllowingStateLoss();
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_left, R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                mMonthCalendar.toLastPager();
                break;
            case R.id.iv_right:
                mMonthCalendar.toNextPager();
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_calendar;
    }

    @Override
    protected int getGravity() {
        return Gravity.BOTTOM;
    }

    public void setOnCalendarListener(OnCalendarListener onCalendarListener) {
        mOnCalendarListener = onCalendarListener;
    }

    public interface OnCalendarListener {
        void onDateSelect(Calendar calendar);
    }
}
