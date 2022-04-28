package com.example.demo3_2_4_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView enter = findViewById(R.id.enter);
        enter.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,DetailMainActivity.class);
            startActivity(intent);
        });
    }
}