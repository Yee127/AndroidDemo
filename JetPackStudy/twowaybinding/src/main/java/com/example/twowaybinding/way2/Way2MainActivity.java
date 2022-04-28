package com.example.twowaybinding.way2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.twowaybinding.R;
import com.example.twowaybinding.databinding.ActivityWay2MainBinding;

public class Way2MainActivity extends AppCompatActivity {

    ActivityWay2MainBinding activityWay2MainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWay2MainBinding = DataBindingUtil.setContentView(this,R.layout.activity_way2_main);
        activityWay2MainBinding.setUserViewModel(new UserViewModel2());
    }
}