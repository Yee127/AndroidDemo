package com.example.chapter5.demo535;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.TextView;

import com.example.chapter5.R;

public class MainActivity535 extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main535);

        int [] colorId = {
                R.color.red,
                R.color.orange,
                R.color.yellow,
                R.color.green,
                R.color.qing,
                R.color.blue,
                R.color.purple,
        };

        int [] tvID = {
                R.id.str1,
                R.id.str2,
                R.id.str3,
                R.id.str4,
                R.id.str5,
                R.id.str6,
                R.id.str7
        };

        for (int i = 0; i < colorId.length; i++) {
            textView = findViewById(tvID[i]);
            textView.setTextSize(15);
            textView.setBackgroundResource(colorId[i]);
            textView.setHeight((int) (getResources().getDimension(R.dimen.basic))*(i+2)/2);
        }
    }
}