package com.example.demo431_call;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demo_417_intent.R;

public class CallMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_main);

        EditText phoneNum = findViewById(R.id.editText_of_call);


        Button btnOfCall = findViewById(R.id.btn_of_call);
        btnOfCall.setOnClickListener(v -> {
            final String phoneNumber = phoneNum.getText().toString();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+phoneNumber));
            startActivity(intent);
        });

    }
}