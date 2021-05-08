package com.zq.jz;

import android.app.Application;
import android.content.Context;

import com.zq.jz.util.SPUtil;

public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        SPUtil.getInstance(this,"config");
    }

    public static Context getAppContext() {
        return sContext;
    }
}
