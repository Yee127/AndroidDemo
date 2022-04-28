package com.example.logindemo.sixthItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logindemo.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SixthItemActivity extends AppCompatActivity{
    private ProgressBar progressBar;
    private static MediaPlayer mp = null;
    private EditText editText;
    private TextView textOfdownload,textOfProgress,textOfTimes;
    private Button button_down,stop_btn;
    private static int count = 0;
//            歌曲存放路径
    private String targetFileAbsPath = "/sdcard/Music";
    int random = new Random().nextInt();
//    随机音乐名
    String songName = String.valueOf(random);

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth_item);

        button_down = findViewById(R.id.download_btn);
//        音乐链接文本
        editText = findViewById(R.id.http_edit);
        textOfdownload = findViewById(R.id.downloading_text);
//        显示进度文本
        textOfProgress = findViewById(R.id.progress_text);
        progressBar = findViewById(R.id.progressbar);
//        下载播放
        button_down.setOnClickListener(v -> {
            String urlStr = editText.getText().toString();
            /**
             * 利用锁  先下载 下载完成后在通知播放
             */
//             下载
            Thread thread_download = new Thread(() ->{
                    downloadThread(urlStr);
                });
//             播放
            Thread thread_play = new Thread(()-> {
                    // 等待下载线程执行完毕在执行
                    try {
                        thread_download.join();
                        playThread();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            });
            thread_download.start();
            thread_play.start();
        });
//       暂停 继续 播放
        stop_btn = findViewById(R.id.stopPlay_btn);
        stop_btn.setOnClickListener(v -> {
            if (isPlaying()){
                if (mp != null){
                    mp.pause();
                    Log.e("提示","暂停播放...");
                    textOfdownload.setText("暂停播放");
                    stop_btn.setText("继 续 播 放");
                    stop_btn.setBackgroundResource(R.drawable.btn_p);
                }
            }else {
                mp.start();
                Log.e("提示","继续播放...");
                textOfdownload.setText("正在播放");
                stop_btn.setText("暂 停 播 放");
                stop_btn.setBackgroundResource(R.drawable.btn_s);
            }
        });
    }

    final Handler handlerSetText = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0x122){
                textOfdownload.setText("正在播放");
            }
            super.handleMessage(msg);
        }
    };
    /**
     * 播放的线程
     */
    private void playThread() {
        lock.lock();
            try {
                playMusic();
                handlerSetText.sendEmptyMessage(0x122);
                Log.e("提示","播放执行ing");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
    }
    /**
     * 下载的线程
     */
    private void downloadThread(String urlStr){
        lock.lock();
            try {
//                  下载
                new DownloadTask(urlStr,targetFileAbsPath,songName).execute();
                Thread.sleep(3000);
                Log.e("提示","下载执行完成！");
                stop_btn.setEnabled(true);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
    }

    /**
     * 处理播放进度条显示
     */
     final Handler  handlerOfProgress = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                // 进度条信息
                int duration = mp.getDuration();  // 获取总时长
//                Log.e("总时长",String.valueOf(duration));
                progressBar.setMax(100);
                if (msg.what == 1){
                    if (isPlaying()){
//                            获取当前进度
                        float currentPosition = (float)mp.getCurrentPosition();
//                            当前进度百分比
                        int position=(int)(currentPosition/(float)duration *100);
//                        Log.e("当前",String.valueOf(position));
                        textOfProgress.setText(position+"%");
                        progressBar.setProgress(position);
                    }
                }
                super.handleMessage(msg);
            }
        };

    /**
     * 下载歌曲任务
     */
     private class DownloadTask extends AsyncTask<String,Integer,String>{
         String urlStr;
         final String targetFileAbsPath;
         String TAG = "提示";
         String song;

         public DownloadTask(String urlStr, String targetFileAbsPath,String song) {
             this.urlStr = urlStr;
             this.targetFileAbsPath = targetFileAbsPath;
             this.song = song;
         }

         @Override
         protected String doInBackground(String... sUrl) {
            File targetFile = new File(targetFileAbsPath,song+".mp3");
            try {
                boolean n = targetFile.createNewFile();
                Log.e(TAG, "Create new file: " + n + ", " + targetFile);
            } catch (IOException e) {
                Log.e(TAG, "run: ", e);
            }
            try {
                URL url = new URL(urlStr);
                URLConnection connection = url.openConnection();
                connection.connect();
                int contentLength = connection.getContentLength(); // 获取内容长度
                BufferedInputStream input = new BufferedInputStream(url.openStream());
                BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(targetFile, false));

                byte[] buffer = new byte[1024];
                long total = 0;
                int count1 ;
                while ((count1 = input.read(buffer)) != -1) {
                    total += count1;
                    int position = (int)(100 * (total / (double) contentLength));
//                    Log.e(TAG, String.format(Locale.CHINA, "Download progress: %.2f%%", 100 * (total / (double) contentLength)));
                    output.write(buffer, 0, count1);
                    publishProgress(position);
                }
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
                Log.e(TAG, "run: ", e);
            }
            return null;
         }

         @Override
         protected void onProgressUpdate(Integer... values) {
             super.onProgressUpdate(values);
             progressBar.setMax(100);
             progressBar.setProgress(values[0]);
             textOfProgress.setText(values[0]+"%");
         }

         @Override
         protected void onPostExecute(String s) {
             textOfdownload.setText("下载完成,即将播放...");
             super.onPostExecute(s);
         }
     }

    //播放
    private void playMusic() {
        if (mp != null) mp.release();
        Uri uri = Uri.parse(targetFileAbsPath+"/"+songName+".mp3");
        Log.e("uri",uri.toString());
        mp = MediaPlayer.create(SixthItemActivity.this,uri);
//        mp = MediaPlayer.create(SixthItemActivity.this,R.raw.s);
        mp.start();
//      使用定时器,每隔200毫秒让handler发送一个空信息
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handlerOfProgress.sendEmptyMessage(1);
            }
        }, 0,200);
        mp.setOnCompletionListener(mp -> {
            playMusic();
            count++;
            textOfTimes = findViewById(R.id.play_times_text);
            textOfTimes.setText("循环次数："+count);
            Log.e("播放次数",String.valueOf(count));
        });
    }

    // 是否正在播放
    public boolean isPlaying(){
        return mp.isPlaying();
    }



}