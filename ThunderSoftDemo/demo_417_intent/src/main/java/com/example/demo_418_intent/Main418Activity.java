package com.example.demo_418_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.demo_417_intent.R;

public class Main418Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main418);

        ImageButton home = findViewById(R.id.img_homeBtn);

        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN); // 设置Intent动作
            intent.addCategory(Intent.CATEGORY_HOME); // 设置Intent种类
            startActivity(intent);
        });
    }
}