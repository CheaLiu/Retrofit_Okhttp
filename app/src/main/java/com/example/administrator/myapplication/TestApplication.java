package com.example.administrator.myapplication;

import android.app.Application;

import com.qi.log.LogUtil;

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化Looger工具
        LogUtil.init(BuildConfig.LOG_DEBUG);
    }
}
