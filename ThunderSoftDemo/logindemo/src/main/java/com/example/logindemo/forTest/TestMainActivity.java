package com.example.logindemo.forTest;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logindemo.R;

public class TestMainActivity extends AppCompatActivity {
    private AlertDialog.Builder ibuilder;

    private TextView text_clear ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
    }
//    private void init(){
//        ((TextView)findViewById(R.id.title)).setText("缓存管理");
//
//        findViewById(R.id.back).setOnClickListener(v -> {
//
//        });
//
//        text_clear = (TextView)this.findViewById(R.id.text_clear) ;
//
//        text_clear.setText(DataCleanManager.getFormatSize(ImageFileCache.calculateImageSize())+"");
//
//        text_clear.setOnClickListener(v -> {
//            switch (v.getId()) {
//
//                case R.id.text_clear:
//
//                    show() ;
//
//                    break;
//
//                case R.id.back:
//
//                    finish();
//
//                    break;
//
//                default:
//
//                    break;
//
//            }
//        });
//
//    }
//
//    private void show() {
//        ibuilder = new AlertDialog.Builder(this);
//        ibuilder.setTitle("清除缓存");
//        ibuilder.setMessage("清除缓存会导致下载的内容删除，是否确定?");
//        ibuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        ibuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                ImageFileCache.ClearCache() ;
//                Toast.makeText(TestMainActivity.this, "缓存已清除", Toast.LENGTH_SHORT).show();
//                text_clear.setText("0.0B");
//                dialog.dismiss();
//            }
//        });
//
//        Dialog dialog = ibuilder.create() ;
//        dialog.show();
//        WindowManager m = getWindowManager();
//        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
//        android.view.WindowManager.LayoutParams p =
//                dialog.getWindow().getAttributes();  //获取对话框当前的参数值
//        p.width = (int) (d.getWidth() * 0.8);    //宽度设置为屏幕的0.9
//        dialog.getWindow().setAttributes(p);    //设置生效
//    }
}