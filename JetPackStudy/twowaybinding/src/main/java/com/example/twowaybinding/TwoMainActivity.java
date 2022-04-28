package com.example.twowaybinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.example.twowaybinding.databinding.ActivityTwoMainBinding;

public class TwoMainActivity extends AppCompatActivity {
    private ActivityTwoMainBinding dataBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_two_main);
        dataBinding.setUserViewModel(new UserViewModel());
    }
}