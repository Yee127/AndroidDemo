package com.example.progress_bar_demo;

import androidx.appcompat.app.AppCompatActivity;



import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
//    保存图片🆔
    private int imgId[] = {R.drawable.a,R.drawable.x,R.drawable.n,R.drawable.hello,R.drawable.m,R.drawable.w};
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.linearLayout1);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setMax(imgId.length);
        new MyTask().execute();
    }


    class MyTask extends AsyncTask<Void,Integer, LinearLayout> {
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
            LinearLayout linearLayout = new LinearLayout(MainActivity.this);
            for (int i = 0; i < imgId.length; i++) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(245,108));
                imageView.setImageResource(imgId[i]);
                linearLayout.addView(imageView);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return linearLayout;
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
            layout.addView(linearLayout);
            super.onPostExecute(linearLayout);
        }
    }
}