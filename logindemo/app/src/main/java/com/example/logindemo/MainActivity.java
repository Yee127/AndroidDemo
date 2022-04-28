package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.tencent.bugly.Bugly;

public class MainActivity extends AppCompatActivity {


    private EditText edituser = null;
    private EditText editpwd = null;
    private Button login_btn = null;
    private CheckBox cb_ischeck = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edituser = findViewById(R.id.username);
        editpwd = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);
        cb_ischeck=findViewById(R.id.cb_rememberMe);
        Bugly.init(getApplicationContext(), "879bdffadb", false);
        login_btn.setOnClickListener(v -> {
//            Untils.error();
            //获取用户名和密码
            String username = edituser.getText().toString();
            String password = editpwd.getText().toString();
            if (username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "用户名或者密码不可为空", Toast.LENGTH_SHORT).show();
            }else {
                if(username.equals("admin")&&password.equals("admin")){
                    Toast.makeText(MainActivity.this, "登录成功，欢迎"+username+"!!!", Toast.LENGTH_SHORT).show();
//                勾选记住我  保存数据
                    if (cb_ischeck.isChecked()){
                        SPDataUtils.saveUserInfo(MainActivity.this,username,password);
                    }
                    //页面跳转
                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                    // 设置要接受的数据
                    Bundle bundle = new Bundle();
                    bundle.putString("username",username);
                    bundle.putString("password",password);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });

//        初始化数据
        initData();


    }

    private void initData() {
        UserInfo userInfo = SPDataUtils.getUserInfo(this);
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getUsername())){
            edituser.setText(userInfo.getUsername());
            editpwd.setText(userInfo.getPassword());
        }
    }
}