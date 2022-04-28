package com.example.logindemo.secondItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.logindemo.R;
import com.example.logindemo.bean.SettingItemBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondItemActivity extends AppCompatActivity {
    private List<Map<String,Object>> setList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String getStr = bundle.getString("str");

        //  竖屏显示
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_second_item);
        }
        //  横屏显示
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.layout_land);
        }
        Toast.makeText(this, getStr, Toast.LENGTH_SHORT).show();
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < SettingItemBean.getTITLES().length; i++) {
            map.put("iconsId",SettingItemBean.getICONS()[i]);
            map.put("title",SettingItemBean.getTITLES()[i]);
            setList.add(map);
        }
        ListView listview = findViewById(R.id.setting_listView);
        listview.setAdapter(new SettingAdapter(setList,this));
        listview.setOnItemClickListener(((parent, view, position, id) -> {
            switch (position){
                case 5:
                    // 横屏显示
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                        replaceFragment(new SettingDetailsFeagment());
                    }
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                        Intent intent1 = new Intent(SecondItemActivity.this,SettingDetailsActivity.class);
                        startActivity(intent1);
                    }
                    break;
            }
        }));
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}