package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ListView listView = (ListView) findViewById(R.id.listView);
        int [] imgId = {
                R.drawable.a,
                R.drawable.m,
                R.drawable.nn,
                R.drawable.w,
                R.drawable.x,
                R.drawable.hello};
        String [] title = {
                "AAA",
                "BBB",
                "CCC",
                "DDD",
                "EEE",
                "FFF",
        };
        List<Map<String,Object>> listItem = new ArrayList<>();
        for (int i = 0; i < imgId.length; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("img",imgId[i]);
            map.put("title",title[i]);
            listItem.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                listItem,
                R.layout.item,
                new String[]{"title", "img"},
                new int[]{R.id.title,R.id.img});
        listView.setAdapter(adapter);
    }
}