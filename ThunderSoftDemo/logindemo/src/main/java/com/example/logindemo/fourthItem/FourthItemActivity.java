package com.example.logindemo.fourthItem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logindemo.R;
import com.example.logindemo.bean.SettingItemBean;
import com.example.logindemo.secondItem.SettingDetailsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FourthItemActivity extends AppCompatActivity {
    private List<Map<String,Object>> setList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_item);

        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < SettingItemBean.getTITLES().length; i++) {
            map.put("iconsId",SettingItemBean.getICONS()[i]);
            map.put("title",SettingItemBean.getTITLES()[i]);
            setList.add(map);
        }
        ListView listview = findViewById(R.id.setting_listView);
        listview.setAdapter(new SettingAdapterFourth(setList,this));
        listview.setOnItemClickListener(((parent, view, position, id) -> {
            switch (position){
                case 5:
                        Intent intent1 = new Intent(FourthItemActivity.this, SettingDetailsActivity.class);
                        startActivity(intent1);
                    break;
            }
        }));
    }
}