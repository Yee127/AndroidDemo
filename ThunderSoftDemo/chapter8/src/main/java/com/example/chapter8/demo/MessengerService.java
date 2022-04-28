package com.example.chapter8.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessengerService extends Service {
    static final int hello = 1;
    class DemoHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {

            switch (msg.what){
                case hello:
                    Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    final Messenger messenger = new Messenger(new DemoHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
        return messenger.getBinder();
    }
}
