package com.example.logindemo.eighthItem;

import android.app.ActivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logindemo.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class EighthItemMainActivity extends AppCompatActivity {
    private Button checkMemoryBtn;
    private TextView freeMemoryText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth_item_main);
        initUI();
    }
    /**
     * 获取assets文件内容
     * @return 返回文本内容
     */
    private String getAssetsContent(){
        try {
            InputStream is = getAssets().open("memory.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            String rel = baos.toString();
            is.close();
            return rel;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initUI() {
        checkMemoryBtn = findViewById(R.id.check_memory_Btn);
        freeMemoryText = findViewById(R.id.freeMemory_text);
        checkMemoryBtn.setOnClickListener(v -> {
            freeMemoryText.setText(getCurrentMemory()+"M");
        });
    }

    private float getCurrentMemory() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        //最大分配内存
        int memory = activityManager.getMemoryClass();
        Log.e("最大分配内存","===>"+memory+"M");
        //最大分配内存获取方法2
        float maxMemory = (float) (Runtime.getRuntime().maxMemory() * 1.0/ (1024 * 1024));
        //当前分配的总内存
        float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0/ (1024 * 1024));
        Log.e("当前分配的总内存","===>"+totalMemory+"M");
        //剩余内存
        float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0/ (1024 * 1024));
        Log.e("剩余的内存","===>"+freeMemory+"M");
        return freeMemory;
    }
}