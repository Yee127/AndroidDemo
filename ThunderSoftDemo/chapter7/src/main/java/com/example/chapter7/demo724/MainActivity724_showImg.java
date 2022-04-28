package com.example.chapter7.demo724;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.chapter7.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity724_showImg extends AppCompatActivity {
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity724_show_img);
        iv = findViewById(R.id.img);
        new Thread(()->{
            final Bitmap bitmap = getPicture("https://images.unsplash.com/photo-1637668737548-8e10b640f728?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1964&q=80");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 发送一个Runnable对象
            iv.post(()->{
                iv.setImageBitmap(bitmap);
            });
        },"获取图片线程").start();
    }

    private Bitmap getPicture(String path) {
        Bitmap bm = null;
        try {
            URL url = new URL(path);
            URLConnection connection = url.openConnection(); // 获取链接
            connection.connect(); // 打开连接
            InputStream is = connection.getInputStream();
            bm = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;
    }
}