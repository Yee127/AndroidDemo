package com.example.demo3_3_4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView = findViewById(R.id.imgView);
        imgView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,HeadActivity.class);
            startActivityForResult(intent,1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 || resultCode == 2){
            Bundle bundle = data.getExtras(); // 获取传递的数据包
            int imgId = bundle.getInt("imgId");
            imgView = findViewById(R.id.imgView);
            imgView.setImageResource(imgId);
        }
    }
}