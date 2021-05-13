package com.zq.jz.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.gyf.immersionbar.ImmersionBar;
import com.zq.jz.R;
import com.zq.jz.base.BaseMvpActivity;
import com.zq.jz.base.BasePresenter;
import com.zq.jz.bean.AccountBean;
import com.zq.jz.bean.event.EventUserIncomePaySelect;
import com.zq.jz.bean.event.EventUserTypeScroll;
import com.zq.jz.db.table.UserAccount;
import com.zq.jz.db.table.UserInComePayType;
import com.zq.jz.ui.adapter.FragmentAdapter;
import com.zq.jz.ui.contract.BookkeepingContract;
import com.zq.jz.ui.dialog.AccountSelectDialog;
import com.zq.jz.ui.fragment.AddPayFragment;
import com.zq.jz.ui.fragment.JzFragment;
import com.zq.jz.ui.presenter.BookkeepingPresenter;
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

/**
 * 记账界面
 */
public class BookkeepingActivity extends BaseMvpActivity implements BookkeepingContract.View {

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
    private boolean mAnimIng;
    private boolean mCalShow = true;
    private ValueAnimator mValueAnimator;
    private BookkeepingPresenter mBookkeepingPresenter;

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {
        mBookkeepingPresenter = new BookkeepingPresenter();
        presenterList.add(mBookkeepingPresenter);
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
        mViewPager2.setUserInputEnabled(false);
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

            @Override
            public void onSelectAccountClick() {
                AccountSelectDialog.show(getSupportFragmentManager(), new AccountSelectDialog.OnDialogListener() {
                    @Override
                    public void onSelect(AccountBean accountBean) {
                        mCalculatorView.setSelectAccount(accountBean.getUserAccount());
                        mBookkeepingPresenter.updateUserConfigSelectAccount(accountBean.getUserAccount().getId());
                    }
                });
            }
        });

        mEtResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCalShow) {
                    onTypeScrollEvent(null);
                }
            }
        });
    }

    @Override
    protected void loadData() {
        mBookkeepingPresenter.getSelectAccount();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTypeScrollEvent(EventUserTypeScroll select) {
        if (mAnimIng) {
            return;
        }
        mCalculatorView.post(new Runnable() {
            @Override
            public void run() {
                int height = mCalculatorView.getMeasuredHeight();
                int start;
                int end;
                if (mValueAnimator != null) {
                    return;
                }
                if (mCalShow) {
                    start = 0;
                    end = -height;
                } else {
                    start = -height;
                    end = 0;
                }
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mCalculatorView.getLayoutParams();
                mValueAnimator = ValueAnimator.ofInt(start, end);
                mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        layoutParams.bottomMargin = (int) animation.getAnimatedValue();
                        mCalculatorView.setLayoutParams(layoutParams);
                    }
                });
                mValueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mAnimIng = false;
                        mCalShow = !mCalShow;
                        mValueAnimator = null;
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        mAnimIng = true;
                    }
                });

                mValueAnimator.setDuration(300);
                mValueAnimator.start();
            }
        });
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

    @Override
    public void onGetSelectAccountSuccess(UserAccount userAccount) {
        if (userAccount == null) {
            showToast("还没有创建账户");
            return;
        }
        mCalculatorView.setSelectAccount(userAccount);
    }
}
