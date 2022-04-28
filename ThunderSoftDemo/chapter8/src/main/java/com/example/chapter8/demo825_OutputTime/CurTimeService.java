package com.example.chapter8.demo825_OutputTime;

import android.app.IntentService;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;

import androidx.annotation.Nullable;



public class CurTimeService extends IntentService {

    public CurTimeService() {
        super("CurTimeService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Time time = new Time();
        time.setToNow();
        String curTime = time.format("%Y-%m-%d %H:%M:%S");
        Log.e("Time",curTime);
    }
}
