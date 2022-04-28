package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivityService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service);
    }

    public void start(View view) {
        Log.e("print","startBtn");
        startService(new Intent(this,MyLocationService.class));
    }

    public void stop(View view) {

        stopService(new Intent(this,MyLocationService.class));
    }
}