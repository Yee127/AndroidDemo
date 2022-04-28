package com.example.demo_417_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.login_btn);
        EditText usernameEdit = findViewById(R.id.username_edit);
        EditText passwordEdit = findViewById(R.id.password_edit);
        btn.setOnClickListener(v -> {
            String username = usernameEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("password",password);
            startActivity(intent);
        });
    }
}