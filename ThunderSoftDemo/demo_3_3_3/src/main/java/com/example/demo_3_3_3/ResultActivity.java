package com.example.demo_3_3_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView genderText = findViewById(R.id.text_res_gender);
        TextView heightText = findViewById(R.id.text_res_height);
        TextView weightText = findViewById(R.id.text_res_weight);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        User user = (User) bundle.getSerializable("user");
        genderText.setText("您是一位"+user.getGender()+"士！");
        heightText.setText("您的身高是"+user.getHeight()+"cm");
        weightText.setText("您的标准体重是"+getWeight(user.getGender(),user.getHeight())+"kg.");

        LinearLayout resLayout = findViewById(R.id.res);
        resLayout.setOnClickListener(v -> {
            finish();
        });
    }

    private String getWeight(String gender, float height) {
        String weight = "";
        DecimalFormat format = new DecimalFormat();
        weight = gender.equals("男")?
                format.format((height-80)*0.7):
                format.format((height-70)*0.6);
        return weight;
    }
}