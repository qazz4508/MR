package com.zq.jz.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.zq.jz.R;
import com.zq.jz.base.BaseMvpActivity;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.ui.adapter.FragmentAdapter;
import com.zq.jz.ui.fragment.JzFragment;
import com.zq.jz.util.LogUtil;
import com.zq.jz.widge.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity {

    @BindView(R.id.tab)
    TabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager2 mViewPager2;

    private List<Fragment> mFragments;
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {

    }

    @Override
    protected void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(JzFragment.newInstance());
        mFragments.add(JzFragment.newInstance());

        mFragmentAdapter = new FragmentAdapter(this, mFragments);
        mViewPager2.setAdapter(mFragmentAdapter);
    }

    @Override
    protected void initListener() {
        mTabLayout.setOnTabListener(new TabLayout.onTabListener() {
            @Override
            public void onTabClick(int index) {
                mViewPager2.setCurrentItem(index,false);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .navigationBarColorInt(Color.WHITE)
                .navigationBarDarkIcon(true).init();
    }
}