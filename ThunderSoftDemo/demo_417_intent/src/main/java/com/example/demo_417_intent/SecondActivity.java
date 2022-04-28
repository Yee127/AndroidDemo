package com.example.demo_417_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView usernameText = findViewById(R.id.username_textView);
        TextView passwordText = findViewById(R.id.password_textView);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        usernameText.setText("Username："+username);
        passwordText.setText("Password："+password);
    }
}