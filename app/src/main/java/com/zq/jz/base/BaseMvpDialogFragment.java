package com.zq.jz.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMvpDialogFragment extends BaseDialogFragment implements BaseView {
    public List<BasePresenter> mPresenters;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        inintPresenter();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void inintPresenter() {
        //初始化Presenter
        if (mPresenters == null) {
            mPresenters = new ArrayList<>();
        }
        createPresenter(mPresenters);
        //presenter与View绑定
        if (null != mPresenters) {
            for (BasePresenter presenter : mPresenters) {
                presenter.attachView(this);
            }
        }
    }

    protected abstract void createPresenter(List<BasePresenter> mPresenters);

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        detachPresenter();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroyView();
    }

    private void detachPresenter() {
        //presenter与activity解绑定
        if (null != mPresenters) {
            while (!mPresenters.isEmpty()) {
                BasePresenter presenter = mPresenters.get(0);
                presenter.detachView();
                mPresenters.remove(0);
            }
        }
    }
}
