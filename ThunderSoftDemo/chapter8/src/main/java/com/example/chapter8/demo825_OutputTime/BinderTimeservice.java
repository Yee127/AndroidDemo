package com.example.chapter8.demo825_OutputTime;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;

import androidx.annotation.Nullable;

public class BinderTimeservice extends Service {
    private final IBinder binder = new LocalBinder();
    public class LocalBinder extends Binder {
        BinderTimeservice getService(){
            return BinderTimeservice.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String getCurrentTime(){
        Time time = new Time();
        time.setToNow();
        String curTime = time.format("%Y-%m-%d %H:%M:%S");
        Log.e("Time",curTime);
        return curTime;
    }
}
