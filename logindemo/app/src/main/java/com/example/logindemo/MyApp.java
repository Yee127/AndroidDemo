package com.example.logindemo;

import android.app.Application;

import com.tencent.bugly.Bugly;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(getApplicationContext(), "879bdffadb", false);
    }
}


