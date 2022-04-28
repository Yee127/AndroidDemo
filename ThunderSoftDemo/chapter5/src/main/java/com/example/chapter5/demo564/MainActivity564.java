package com.example.chapter5.demo564;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.chapter5.R;

public class MainActivity564 extends AppCompatActivity {
    private Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main564);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);

        button1.setOnClickListener(v -> {
            Button button = (Button) v;
            button.setEnabled(false); // 将btn1按钮设置不可用
            button.setText("我不可用了！！！");
            Toast.makeText(this, "此按钮已不可用！", Toast.LENGTH_SHORT).show();
        });

        button2.setOnClickListener(v -> {
            button1.setEnabled(true); // 将btn1按钮设置可用
            button1.setText("我是可用按钮！！！");
//            Toast.makeText(this, "此按钮已不可用！", Toast.LENGTH_SHORT).show();
        });


    }
}