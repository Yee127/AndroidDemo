package com.example.chapter8.demo825_OutputTime;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessengerShowTimeService extends Service {
    public static final int CURRENT_TIME = 0;
    private class IncomingHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == CURRENT_TIME){
                Time time = new Time();
                time.setToNow();
                String curTime = time.format("%Y-%m-%d %H:%M:%S");
                Log.e("Time",curTime);
                Toast.makeText(MessengerShowTimeService.this, "当前时间："+curTime, Toast.LENGTH_SHORT).show();
            }else {
                super.handleMessage(msg);
            }
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Messenger messenger = new Messenger(new IncomingHandler());
        return messenger.getBinder();
    }
}
