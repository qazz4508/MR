package com.zq.jz.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseMvpFragment extends Fragment implements BaseView{

    private List<BasePresenter> mPresenterList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.getLayoutId(), container, false);
        ButterKnife.bind(this, view);

        initPresenter();

        queueMethod();
        return view;
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

    protected void initListener() {
    }


    protected void loadData() {

    }


    protected abstract int getLayoutId();
}
