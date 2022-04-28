package com.example.dialogboxdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView quit = findViewById(R.id.quit_imgBtn);
        ImageSwitcher

        quit.setOnClickListener(v -> {
//            新建对话框
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setIcon(R.drawable.warn);
            alertDialog.setTitle("退出？");
            alertDialog.setMessage("确认要退出吗？");
            //取消按钮
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"点错了",(dialogInterface,which)->{
            });
//            确认按钮
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"是的",(dialog, which) -> {
                finish(); // 返回系统主界面
            });
            alertDialog.show(); // 显示对话框
        });

    }
}