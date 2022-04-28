package com.example.chapter8.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import com.example.chapter8.R;

public class MessengerMainActivity extends AppCompatActivity {
   Messenger messenger = null;
   boolean bound;
   private ServiceConnection connection = new ServiceConnection() {
       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {
           messenger = new Messenger(service);
           bound = true;
       }

       @Override
       public void onServiceDisconnected(ComponentName name) {
            messenger = null;
            bound = false;
       }
   };

   public void sayHello(){
       if (!bound) return;
       Message message = Message.obtain(null,MessengerService.hello,0,0);
       try {
           messenger.send(message);
       } catch (RemoteException e) {
           e.printStackTrace();
       }
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_main);
        sayHello();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this,MessengerService.class),connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound)
        {
            unbindService(connection);
            bound = false;
        }
    }
}