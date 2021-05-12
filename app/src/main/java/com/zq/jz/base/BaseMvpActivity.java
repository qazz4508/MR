package com.zq.jz.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseMvpActivity extends AppCompatActivity implements BaseView {

    protected ImmersionBar mImmersionBar;
    private List<BasePresenter> mPresenterList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        ButterKnife.bind(this);

        initImmersionBar();

        initPresenter();

        queueMethod();
    }

    private void initPresenter() {
        if (mPresenterList == null) {
            mPresenterList = new ArrayList<>();
        }
        addPresenter(mPresenterList);
        for (BasePresenter presenter : mPresenterList) {
            presenter.attachView(this);
        }
    }

    protected abstract void addPresenter(List<BasePresenter> presenterList);

    protected void queueMethod() {
        initData();
        initView();
        initListener();
        loadData();
    }

    protected void initData() {

    }

    protected void initView() {

    }

    protected void initListener() {
    }


    protected void loadData() {

    }

    protected abstract int getLayoutId();

    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .transparentNavigationBar()
                .navigationBarAlpha(0.01f)
                .navigationBarDarkIcon(true);
        mImmersionBar.init();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachPresenter();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe
    public void onEventMainThread(String event) {
    }

    private void detachPresenter() {
        //presenter与activity解绑定
        if (null != mPresenterList) {
            while (!mPresenterList.isEmpty()) {
                BasePresenter presenter = mPresenterList.get(0);
//                presenter.dispose();
                presenter.detachView();
                mPresenterList.remove(0);
            }
        }
    }
}
