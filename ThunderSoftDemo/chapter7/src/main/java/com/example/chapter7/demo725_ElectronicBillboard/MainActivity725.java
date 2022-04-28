package com.example.chapter7.demo725_ElectronicBillboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chapter7.R;

import java.util.Random;

public class MainActivity725 extends AppCompatActivity implements Runnable{
    private TextView tv;
    private ImageView iv;
    private Handler handler;
    private int[] path = {R.drawable.java,
            R.drawable.android,
            R.drawable.python,
            R.drawable.youtube
    };

    private String [] title ={
            "java 从入门到入土",
            "android bug 制造机",
            "人生苦短 我用Python",
            "YouTube 油土鳖 ",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main725);
        iv = findViewById(R.id.img_ad);
        new Thread(this).start();
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                    tv = findViewById(R.id.text);
                    if (msg.what == 0x101){
                        tv.setText(msg.getData().getString("title"));
                        iv.setImageResource(path[msg.arg1]);
                    }
                    super.handleMessage(msg);
            }
        };
    }

    @Override
    public void run() {
        int index = 0;
        while (!Thread.currentThread().isInterrupted()){
            index = new Random().nextInt(path.length);
            Message m = handler.obtainMessage();
            m.arg1 = index; // 保存要显示广告图片的索引值
            Bundle bundle = new Bundle();
            m.what = 0x101; // 设置标识
            bundle.putString("title",title[index]);
            m.setData(bundle); // 将Bundle对象保存的Message中
            handler.sendMessage(m);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}