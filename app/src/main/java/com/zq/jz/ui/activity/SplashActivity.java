package com.zq.jz.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.gyf.immersionbar.BarHide;
import com.zq.jz.R;
import com.zq.jz.base.BaseMvpActivity;
import com.zq.jz.base.BasePresenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseMvpActivity {

    protected static final long JUMP_TIME_OUT = 1000;

    private boolean mDestroy;

    private Disposable mDisposable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void addPresenter(List<BasePresenter> presenterList) {
        
    }

    @Override
    protected void initView() {
        mDisposable = Observable.just("")
                .delay(JUMP_TIME_OUT, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        go2Main();
                    }
                });
    }

    private void go2Main() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mDestroy = true;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        super.onDestroy();
    }
}
