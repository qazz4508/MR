package com.zq.jz.ui.activity;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.zq.jz.R;
import com.zq.jz.base.BaseMvpActivity;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.ui.adapter.FragmentAdapter;
import com.zq.jz.ui.fragment.AddPayFragment;
import com.zq.jz.ui.fragment.JzFragment;
import com.zq.jz.widge.MyPagerIndicator;
import com.zq.jz.widge.MyPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BookkeepingActivity extends BaseMvpActivity {

    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.vp)
    ViewPager2 mViewPager2;

    private String[] mTitles = new String[]{"支出", "收入", "转账"};
    private List<Fragment> mFragments;
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {

    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(AddPayFragment.newInstance());
        mFragments.add(AddPayFragment.newInstance());
        mFragments.add(AddPayFragment.newInstance());
    }

    @Override
    protected void initView() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setIndicatorOnTop(false);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                MyPagerTitleView titleView = new MyPagerTitleView(context);
                titleView.setText(mTitles[index]);
                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return new MyPagerIndicator(context);
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        bind(mMagicIndicator, mViewPager2);

        mFragmentAdapter = new FragmentAdapter(this, mFragments);
        mViewPager2.setAdapter(mFragmentAdapter);
    }

    public static void bind(final MagicIndicator magicIndicator, ViewPager2 viewPager) {
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bookkeeping;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).init();
    }
}
