package com.zq.jz.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseMvpFragment extends Fragment implements BaseView{

    private List<BasePresenter> mPresenterList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.getLayoutId(), container, false);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

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

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachPresenter();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
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

    @Subscribe
    public void onEventMainThread(String event) {
    }

    protected abstract int getLayoutId();
}
