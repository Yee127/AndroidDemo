package com.example.logindemo.thirdItem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.logindemo.R;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class ThirdItemActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetWorkChangeReceiver networkChangeReceiver;
    private final static String ACTION_NETWORK = "android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_item);
        Intent intent = getIntent();
        int intValue = intent.getIntExtra("intValue",0);
        byte [] byteValue = intent.getByteArrayExtra("byteValue");
        Serializable serializableValue = intent.getSerializableExtra("serializableValue");

        TextView text1 = findViewById(R.id.textView1_of_ThirdItemActivity);
        TextView text2 = findViewById(R.id.textView2_of_ThirdItemActivity);
        TextView text3 = findViewById(R.id.textView3_of_ThirdItemActivity);

        String msg = ((Integer) intValue != null && byteValue.length != 0 && serializableValue != null) ?"😀数据正确接收！！！":"⚠️数据接收失败！！！";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        text1.setText("int类型："+intValue);
        try {
            text2.setText("byteArray类型："+new String(byteValue,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        text3.setText("serializable类型："+serializableValue);

        intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_NETWORK);
        networkChangeReceiver = new NetWorkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }
}