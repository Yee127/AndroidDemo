package com.example.chapter7.demo731ColorLight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chapter7.R;

import java.util.Random;

public class MainActivity731 extends AppCompatActivity {
    private Handler handler;
    private static LinearLayout layout;
    private static TextView[] tv = new TextView[14];

    private int [] colors = {
            R.color.red,
            R.color.orange,
            R.color.yellow,
            R.color.green,
            R.color.qing,
            R.color.blue,
            R.color.purple
    };

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main731);

       layout =  findViewById(R.id.ll);
       int height = this.getResources().getDisplayMetrics().widthPixels; // 获取屏幕高度
        for (int i = 0; i < tv.length; i++) {
            tv[i] = new TextView(this);
            tv[i].setWidth(this.getResources().getDisplayMetrics().widthPixels); // 设置文本框宽
            tv[i].setHeight(height/tv.length);
            layout.addView(tv[i]);
        }

        new Thread(()->{
           while (!Thread.currentThread().isInterrupted()){
               Message m = handler.obtainMessage();
               m.what = 0x101;
               handler.sendMessage(m);
               try {
                   Thread.sleep(new Random().nextInt(500));
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }).start();

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                int temp = 0;
                if (msg.what == 0x101){
                    for (int i = 0; i < tv.length; i++) {
                        temp = new Random().nextInt(colors.length);
//                        去掉重读并相邻的颜色
                        if (index == temp){
                            temp++;
                            if (temp == colors.length) temp = 0;
                        }
                        index = temp;
                        tv[i].setBackgroundResource(colors[index]);
                    }
                }
                super.handleMessage(msg);
            }
        };

    }
}