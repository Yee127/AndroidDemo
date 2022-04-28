package com.example.demo3_2_4_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView textAbout = findViewById(R.id.about_text);
        textAbout.setOnClickListener(v -> {
            finish();
        });
    }
}