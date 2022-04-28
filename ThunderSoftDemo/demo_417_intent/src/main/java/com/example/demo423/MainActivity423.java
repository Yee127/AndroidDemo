package com.example.demo423;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageSwitcher;

import com.example.demo_417_intent.R;

public class MainActivity423 extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main423);

        btn = findViewById(R.id.btn_Of_423);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("test_action");
//            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        });
    }
}