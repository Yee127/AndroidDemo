package com.example.logindemo.secondItem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;

import com.example.logindemo.R;

public class SettingDetailsActivity extends AppCompatActivity {
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.right_fragment_show_details);
        seekBar = findViewById(R.id.light_bar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingDetailsActivity.this.setScreenBrightness((float) seekBar.getProgress() / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void setScreenBrightness(float num) {
        //取得屏幕的属性
        WindowManager.LayoutParams layoutParams = super.getWindow().getAttributes();
        //设置屏幕的亮度
        layoutParams.screenBrightness = num;
        //重新设置窗口的属性
        super.getWindow().setAttributes(layoutParams);
    }

}