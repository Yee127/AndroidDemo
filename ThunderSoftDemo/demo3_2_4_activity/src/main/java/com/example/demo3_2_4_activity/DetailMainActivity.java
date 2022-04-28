package com.example.demo3_2_4_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_main);

        ImageView quit = findViewById(R.id.quit_imgBtn);
        quit.setOnClickListener(v -> {
            finish();
        });

        TextView detail = findViewById(R.id.detail_text);
        detail.setOnClickListener(v -> {
            Intent intent = new Intent(DetailMainActivity.this,AboutActivity.class);
            startActivity(intent);
        });
    }
}
