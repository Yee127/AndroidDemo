package com.yee.imgbuttondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btn = findViewById(R.id.imgBtn);
        btn.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this,"看见没，我刚才变样了！！！！",Toast.LENGTH_SHORT).show();
        });
    }
}
