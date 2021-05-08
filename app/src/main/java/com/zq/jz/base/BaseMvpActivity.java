package com.zq.jz.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseMvpActivity extends AppCompatActivity implements BaseView{

    protected ImmersionBar mImmersionBar;
    private List<BasePresenter> mPresenterList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);

        initImmersionBar();

        initPresenter();

        queueMethod();
    }

    private void initPresenter() {
        if(mPresenterList==null){
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

    protected void initListener(){}


    protected void loadData() {

    }

    protected abstract int getLayoutId();

    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .transparentNavigationBar()
                .navigationBarAlpha(0.01f)
                .navigationBarDarkIcon(true);
        mImmersionBar.init();
    }
}
