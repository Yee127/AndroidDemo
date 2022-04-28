package com.example.chapter7.demo715;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.chapter7.R;

public class MainActivity715_Runnable extends AppCompatActivity implements Runnable{
    private Thread thread;
    private int i;
    private Button btn_start,btn_interrupted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity715_runnable);
        btn_start = findViewById(R.id.StartThread_btn);
        btn_interrupted = findViewById(R.id.interruptedThread_btn);
        btn_start.setOnClickListener(v -> {
            i = 0;
            thread = new Thread(MainActivity715_Runnable.this);
            thread.start();
            Toast.makeText(this, "线程开始", Toast.LENGTH_SHORT).show();
        });

        btn_interrupted.setOnClickListener(v -> {
            if (thread != null){
                thread.interrupt();
                thread = null;
                Log.e("提示","线程中断！");
                Toast.makeText(this, "线程中断", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            i++;
            Log.e("---->i",String.valueOf(i));
        }
    }

    @Override
    protected void onDestroy() {
        if (thread != null){
            thread.interrupt();
            thread = null;
            Toast.makeText(this, "线程中断", Toast.LENGTH_SHORT).show();
        }
        super.onDestroy();
    }
}