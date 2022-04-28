package com.example.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logindemo.Utils.SPDataUtils;
import com.example.logindemo.bean.UserInfo;
import com.example.logindemo.eighthItem.EighthItemMainActivity;
import com.example.logindemo.fifthItem.FifthItemActivity;
import com.example.logindemo.firstItem.ShowImgActivity;
import com.example.logindemo.fourthItem.FourthItemActivity;
import com.example.logindemo.secondItem.SecondItemActivity;
import com.example.logindemo.seventhItem.SeventhItemActivity;
import com.example.logindemo.sixthItem.SixthItemActivity;
import com.example.logindemo.thirdItem.ThirdItemActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainInterfaceActivity extends AppCompatActivity {
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
        Bundle bundle = this.getIntent().getExtras();
        String username = bundle.getString("username");
//        String password = bundle.getString("password");
        ListView listView = (ListView) findViewById(R.id.listView);

        List<String> usernameLists = new ArrayList<>();
        UserInfo userInfo = SPDataUtils.getUserInfo(this);
//        Log.e("userInfo：",userInfo.getUsername());

            usernameLists.add(username);
            usernameLists.add("设置");
            usernameLists.add("监听");
            usernameLists.add("设置2");
            usernameLists.add("扫描");
            usernameLists.add("音乐播放");
            usernameLists.add("垃圾文件扫描");


        List<Map<String,Object>> listItem = new ArrayList<>();
        for (String user: usernameLists) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i++);
            map.put("username",user);
//            map.put("password",password);
            listItem.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                listItem,
                R.layout.item,
                new String[] {"id", "username"},
                new int[]{R.id.id,R.id.username});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            switch (position){
                case 0:
                    //页面跳转
                    Intent intent0 = new Intent(MainInterfaceActivity.this, ShowImgActivity.class);
                    startActivity(intent0);
                    break;
                case 1:
                    //页面跳转
                    String str = "我是传过来的值...";
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("str",str);
                    Intent intent1 = new Intent(MainInterfaceActivity.this, SecondItemActivity.class);
                    intent1.putExtra("data",bundle1);
                    startActivity(intent1);
                    break;
                case 2:
//                    设置要传输的数据
                    int intValue = 1;
                    byte [] byteValue = {97,98,99,100,101};
                    Serializable serializableValue = 3;

                    Intent intent2 = new Intent(MainInterfaceActivity.this, ThirdItemActivity.class);
                    intent2.putExtra("intValue",intValue);
                    intent2.putExtra("byteValue",byteValue);
                    intent2.putExtra("serializableValue",serializableValue);
                    startActivity(intent2);
                    break;
                case 3:
                    Intent intent3 = new Intent(MainInterfaceActivity.this, FourthItemActivity.class);
                    startActivity(intent3);
                    break;
                case 4:
                    Intent intent4 = new Intent(MainInterfaceActivity.this, FifthItemActivity.class);
                    startActivity(intent4);
                    break;
                case 5:
                    Intent intent5 = new Intent(MainInterfaceActivity.this, SixthItemActivity.class);
                    startActivity(intent5);
                    break;
                case 6:
                    Intent intent6 = new Intent(MainInterfaceActivity.this, SeventhItemActivity.class);
                    startActivity(intent6);
                    break;
                case 7:
                    Intent intent7 = new Intent(MainInterfaceActivity.this, EighthItemMainActivity.class);
                    startActivity(intent7);
                    break;
            }
        });

    }
}