package com.example.demo_3_3_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText height_editText = null;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button subBtn = findViewById(R.id.submit_btn);
        TextView tipText = findViewById(R.id.tip_text);
        RadioGroup gender = findViewById(R.id.gender_radio);
        subBtn.setOnClickListener(v -> {
            height_editText = findViewById(R.id.height_editText);
            String strHeight = height_editText.getText().toString();
            user = new User();
            if (strHeight.isEmpty()) {
                tipText.setText("不输入身高没办法计算哟！");
                return;
            }
            int height = Integer.parseInt(strHeight);  // 获取身高
            for (int i = 0; i < gender.getChildCount(); i++) {
                RadioButton genderChildAt = (RadioButton) gender.getChildAt(i);
                if (genderChildAt.isChecked()){
                    user.setGender(genderChildAt.getText().toString());
                    break;
                }
            }
            user.setHeight(height); // 设置身高
            Bundle bundle = new Bundle();
            bundle.putSerializable("user",user);
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}