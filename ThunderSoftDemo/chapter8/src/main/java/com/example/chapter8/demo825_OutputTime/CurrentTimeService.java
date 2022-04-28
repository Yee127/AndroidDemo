package com.example.chapter8.demo825_OutputTime;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;

import androidx.annotation.Nullable;

public class CurrentTimeService  extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Time time = new Time();
        time.setToNow();
        String curTime = time.format("%Y-%m-%d %H:%M:%S");
        Log.e("当前时间",curTime);
        return super.onStartCommand(intent, flags, startId);
    }
}
