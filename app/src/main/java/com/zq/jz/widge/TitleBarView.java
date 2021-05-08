package com.zq.jz.widge;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zq.jz.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TitleBarView extends FrameLayout {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_right)
    TextView mTvRight;

    private String mTitle;

    private OnBarListener mOnBackListener;
    private String mRightText;

    public TitleBarView(@NonNull Context context) {
        this(context, null);
    }

    public TitleBarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.view_title_bar, this);
        ButterKnife.bind(this, view);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView);
        if (array != null) {
            mTitle = array.getString(R.styleable.TitleBarView_bar_title);
            mRightText = array.getString(R.styleable.TitleBarView_bar_right_text);
            array.recycle();
        }
        init();
    }

    private void init() {
        mTvTitle.setText(mTitle);
        mTvRight.setText(mRightText);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    @OnClick(R.id.iv_back)
    public void onBackClick() {
        if (mOnBackListener != null) {
            mOnBackListener.onBack();
        }
    }

    @OnClick(R.id.tv_right)
    public void onRightClick() {
        if (mOnBackListener != null) {
            mOnBackListener.onRight();
        }
    }

    public void setOnBackListener(OnBarListener onBackListener) {
        mOnBackListener = onBackListener;
    }

    public interface OnBarListener {
        void onBack();

        void onRight();
    }
}
