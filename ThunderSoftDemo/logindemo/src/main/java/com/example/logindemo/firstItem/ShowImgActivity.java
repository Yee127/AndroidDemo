package com.example.logindemo.firstItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.example.logindemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowImgActivity extends AppCompatActivity {
    private LinearLayout layout1;
    private LinearLayout layout2;
    private ProgressBar progressBar;
    private SimpleAdapter adapter;
    //    保存图片🆔
    private List<Integer> listImgId = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_img);
        listImgId.add(R.drawable.a);
        listImgId.add(R.drawable.x);
        listImgId.add(R.drawable.n);
        listImgId.add(R.drawable.m);
        listImgId.add(R.drawable.w);
        listImgId.add(R.drawable.hello);
        layout1 = findViewById(R.id.linearLayout1);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setMax(listImgId.size());

//      处理进度条任务
        new MyTask().execute();
//        长按操作
        longPress();
    }

    //        长按操作
    private void longPress() {
        ListView imgListView = findViewById(R.id.listViewItem);
        imgListView.setOnItemLongClickListener((parent, view, position,id) -> {
            AlertDialog alertDialog = new AlertDialog.Builder(ShowImgActivity.this).create();
            alertDialog.setIcon(R.drawable.warn);
            alertDialog.setTitle("警告");
            alertDialog.setMessage("确定要删除吗？");
            //取消按钮
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"点错了",(dialogInterface, which)->{
            });
//            确认按钮
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定",(dialog, which) -> {
                Integer remove = listImgId.remove(position);
                Log.e("remove:",remove.toString());
                handler.sendEmptyMessage(1);
            });
            alertDialog.show(); // 显示对话框
            return true;
        });
    }


    // 处理显示图片列表
    Handler handler = new Handler((msg) ->{
            if(msg.what == 1){
                showImgList();
            }
            return false;
        }
    );
    private void showImgList() {
        List<Map<String, Object>> listImgItems = new ArrayList<>();
        for (Integer listImg : listImgId) {
            Map mapImg = new HashMap();
            mapImg.put("img", listImg);
            listImgItems.add(mapImg);
        }
        adapter = new SimpleAdapter(ShowImgActivity.this, listImgItems, R.layout.imgitems, new String[]{"img"}, new int[]{R.id.imgItem});
        ListView listView = findViewById(R.id.listViewItem);
        listView.setAdapter(adapter);
    }
    class MyTask extends AsyncTask<Void, Integer, LinearLayout> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        /**
         * 要执行的耗时任务
         * @param voids
         * @return
         */
        @Override
        protected LinearLayout doInBackground(Void... voids) {
            layout2 = new LinearLayout(ShowImgActivity.this);
            for (int i = 1; i < listImgId.size(); i++) {
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return layout2;
        }
        /**
         * 更新进度
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            super.onProgressUpdate(values);
        }
        /**
         * 任务执行后
         * @param linearLayout
         */
        @Override
        protected void onPostExecute(LinearLayout linearLayout) {
            progressBar.setVisibility(View.GONE);
            handler.sendEmptyMessage(1);
            layout1.addView(linearLayout);
            super.onPostExecute(linearLayout);
        }
    }
}