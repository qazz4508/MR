package com.zq.jz.widge;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zq.jz.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabLayout extends ConstraintLayout {

    @BindView(R.id.iv_tab1)
    ImageView mIvTab1;
    @BindView(R.id.iv_tab2)
    ImageView mIvTab2;
    @BindView(R.id.iv_tab1_bg)
    ImageView mIvTab1Bg;
    @BindView(R.id.iv_tab2_bg)
    ImageView mIvTab2Bg;
    @BindView(R.id.tv_tab1)
    TextView mTvTab1;
    @BindView(R.id.tv_tab2)
    TextView mTvTab2;

    private View mView;

    private final int[] mSelectTab = new int[]{R.drawable.ic_tab_jz_s, R.drawable.ic_tab_zc_s};
    private final int[] mUnSelectTab = new int[]{R.drawable.ic_tab_jz_un, R.drawable.ic_tab_zc_un};

    private int mSelectIndex;
    private List<ImageView> mIvTabs = new ArrayList<>();
    private List<ImageView> mIvTabBgs = new ArrayList<>();
    private List<TextView> mTvTabs = new ArrayList<>();
    private final long mAnimDuration = 150;

    private onTabListener mOnTabListener;

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mView = inflate(context, R.layout.layout_tab, this);
        ButterKnife.bind(this, mView);
        init();
    }

    private void init() {
        mIvTabs.add(mIvTab1);
        mIvTabs.add(mIvTab2);
        mIvTabBgs.add(mIvTab1Bg);
        mIvTabBgs.add(mIvTab2Bg);
        mTvTabs.add(mTvTab1);
        mTvTabs.add(mTvTab2);

        refresh();
    }

    private void refresh() {
        for (int i = 0; i < mIvTabs.size(); i++) {
            ImageView imageView = mIvTabs.get(i);
            ImageView ivBg = mIvTabBgs.get(i);
            TextView tv = mTvTabs.get(i);
            if (i == mSelectIndex) {
                imageView.setImageResource(mSelectTab[i]);
                ivBg.setAlpha(1f);
                tv.setTextColor(getResources().getColor(R.color.primary));
                showBgAnim(ivBg);
            } else {
                ivBg.setAlpha(0f);
                imageView.setImageResource(mUnSelectTab[i]);
                tv.setTextColor(Color.parseColor("#bfbfbf"));
            }
        }
    }


    @OnClick({R.id.cl_tab1,R.id.cl_tab2})
    public void onTab1Click(View view) {
        switch (view.getId()){
            case R.id.cl_tab1:
                if (mSelectIndex == 0) {
                    return;
                }
                mSelectIndex = 0;
                break;
            case R.id.cl_tab2:
                if (mSelectIndex == 1) {
                    return;
                }
                mSelectIndex = 1;
                break;
        }
        if(mOnTabListener!=null){
            mOnTabListener.onTabClick(mSelectIndex);
        }
        refresh();
    }

    private void showBgAnim(ImageView imageView) {
        ObjectAnimator xAnim = ObjectAnimator.ofFloat(imageView, "scaleX", 0.5f, 1f);
        xAnim.setDuration(mAnimDuration);
        ObjectAnimator yAnim = ObjectAnimator.ofFloat(imageView, "scaleY", 0.5f, 1f);
        yAnim.setDuration(mAnimDuration);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(xAnim, yAnim);
        animatorSet.start();
    }

    public void setOnTabListener(onTabListener onTabListener) {
        mOnTabListener = onTabListener;
    }

    public interface onTabListener {
        void onTabClick(int index);
    }
}
