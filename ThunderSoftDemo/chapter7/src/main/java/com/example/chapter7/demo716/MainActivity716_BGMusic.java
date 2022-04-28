package com.example.chapter7.demo716;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.chapter7.R;

public class MainActivity716_BGMusic extends AppCompatActivity {
    private Thread thread;
    private static MediaPlayer mediaPlayer;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity716_bgmusic);

        button = findViewById(R.id.play_music_btn);
        button.setOnClickListener(v -> {
            ((Button) v).setEnabled(false); // 设置按钮不可用
            new Thread(()->{
                playBGSound();  // 播放背景音乐
            }).start();
        });
    }

    private void playBGSound() {
        if (mediaPlayer != null) mediaPlayer.release();

        mediaPlayer = MediaPlayer.create(MainActivity716_BGMusic.this,R.raw.shelter);
        mediaPlayer.start();
        Log.e("提示","播放音乐ing...");
        mediaPlayer.setOnCompletionListener(mp -> {
            try {
                Thread.sleep(5000);
                playBGSound();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        if (thread != null) thread = null;
        super.onDestroy();
    }
}