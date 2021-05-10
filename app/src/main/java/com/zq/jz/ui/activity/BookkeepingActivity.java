package com.zq.jz.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.gyf.immersionbar.ImmersionBar;
import com.zq.jz.R;
import com.zq.jz.base.BaseMvpActivity;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.event.EventUserIncomePaySelect;
import com.zq.jz.db.table.UserInComePayType;
import com.zq.jz.ui.adapter.FragmentAdapter;
import com.zq.jz.ui.fragment.AddPayFragment;
import com.zq.jz.ui.fragment.JzFragment;
import com.zq.jz.util.LogUtil;
import com.zq.jz.widge.CalculatorView;
import com.zq.jz.widge.MyPagerIndicator;
import com.zq.jz.widge.MyPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BookkeepingActivity extends BaseMvpActivity {

    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.vp)
    ViewPager2 mViewPager2;
    @BindView(R.id.tv_type_name)
    TextView mTvTypeName;
    @BindView(R.id.cv)
    CalculatorView mCalculatorView;
    @BindView(R.id.et_result)
    EditText mEtResult;
    @BindView(R.id.tv_result)
    TextView mTvResult;

    private String[] mTitles = new String[]{"支出", "收入", "转账"};
    private List<Fragment> mFragments;
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {

    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(AddPayFragment.newInstance(1));
        mFragments.add(AddPayFragment.newInstance(2));
        mFragments.add(AddPayFragment.newInstance(3));
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
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager2.setCurrentItem(index);
                    }
                });
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

    @Override
    protected void initListener() {
        mCalculatorView.setListener(new CalculatorView.OnCalResultListener() {

            @Override
            public void onResult(String processText, String result) {
                mTvResult.setText(result);
                mEtResult.setText(processText);
                if (processText.equals(result)) {
                    mEtResult.setVisibility(View.VISIBLE);
                    mEtResult.setTextSize(26);
                    mTvResult.setVisibility(View.GONE);
                } else {
                    mEtResult.setVisibility(View.VISIBLE);
                    mEtResult.setTextSize(12);
                    mTvResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onOk(String resule) {

            }
        });
    }

    public void bind(final MagicIndicator magicIndicator, ViewPager2 viewPager) {
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
                if (mFragments.size() > 0 && mFragments.get(position) instanceof AddPayFragment) {
                    UserInComePayType comePayType = ((AddPayFragment) mFragments.get(position)).getCurrType();
                    if (null != comePayType) {
                        onTypeSelectEvent(new EventUserIncomePaySelect(comePayType));
                    }
                } else {
                    mTvTypeName.setText("转账");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTypeSelectEvent(EventUserIncomePaySelect select) {
        if (select.getUserInComePayType() != null) {
            UserInComePayType userInComePayType = select.getUserInComePayType();
            if (TextUtils.isEmpty(userInComePayType.getAnotherName())) {
                mTvTypeName.setText(select.getUserInComePayType().getName());
            } else {
                mTvTypeName.setText(select.getUserInComePayType().getAnotherName());
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Fragment fragment = mFragments.get(mViewPager2.getCurrentItem());
        if (fragment instanceof AddPayFragment) {
            ((AddPayFragment) fragment).reloadData();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bookkeeping;
    }

    @OnClick(R.id.iv_back)
    public void back() {
        finish();
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .navigationBarColor(R.color.white)
                .navigationBarDarkIcon(true);
        mImmersionBar.init();
    }
}
