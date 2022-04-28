package com.example.chapter8.demo825_OutputTime;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.chapter8.R;


public class MainActivity825 extends AppCompatActivity {
    private Button button,button2,button3,button4;
    BinderTimeservice cts;
    boolean bound;
    Messenger messenger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main825);

        button = findViewById(R.id.show_time_btn);
        button2 = findViewById(R.id.show_time_btn2);

        button.setOnClickListener(v -> {
            startService(new Intent(MainActivity825.this,CurTimeService.class));
        });
        button2.setOnClickListener(v -> {
            startService(new Intent(MainActivity825.this,CurrentTimeService.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        button3 = findViewById(R.id.show_time_btn3);
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity825.this, BinderTimeservice.class);
            bindService(intent,sc,BIND_AUTO_CREATE);
            if (bound){
                Toast.makeText(this, cts.getCurrentTime(), Toast.LENGTH_SHORT).show();
            }
        });

        button4 = findViewById(R.id.show_time_btn4);
        button4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity825.this, MessengerShowTimeService.class);
            bindService(intent,connection,BIND_AUTO_CREATE);
            if (bound){
                Message message = Message.obtain(null, MessengerShowTimeService.CURRENT_TIME, 0, 0);
                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound){
            bound = false;
            unbindService(sc);
            unbindService(connection);
        }
    }

    private ServiceConnection sc = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BinderTimeservice.LocalBinder binder = (BinderTimeservice.LocalBinder) service;
            cts = binder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound =false;
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
            bound =false;
        }
    };
}