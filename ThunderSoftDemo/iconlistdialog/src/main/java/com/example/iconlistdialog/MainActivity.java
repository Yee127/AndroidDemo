package com.example.iconlistdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private int [] imgId = {
            R.drawable.shopping,
            R.drawable.change,
            R.drawable.password,
            R.drawable.about,
            R.drawable.quit,
    };
    private final String[] title = {
            "在线购物",
            "切换用户",
            "修改密码",
            "关于我们",
            "退出系统",
    };
    List<Map<String,Object>> listItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < imgId.length; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("img",imgId[i]);
            map.put("title",title[i]);
            listItems.add(map);
        }
        final SimpleAdapter adapter = new SimpleAdapter(this,listItems,
                R.layout.items,new String[]{"title","img"},
                new int []{R.id.title,R.id.img});
        ImageButton imgBtn = findViewById(R.id.m_imgBtn);
        imgBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setIcon(R.drawable.more);
            builder.setTitle("更多");
            builder.setAdapter(adapter,(dialog, which) -> {
                Toast.makeText(MainActivity.this, "您选择了："+title[which], Toast.LENGTH_SHORT).show();
            });
            builder.create().show();
        });
    }
}