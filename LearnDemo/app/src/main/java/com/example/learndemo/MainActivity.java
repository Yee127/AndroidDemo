package com.example.learndemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.example.learndemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
//        myViewModel.getNum().observe(this,integer -> {
//            binding.textView.setText(String.valueOf(integer));
//        });
//        binding.button.setOnClickListener(view -> {
//            myViewModel.add();
//        });
        binding.setData(myViewModel);
        binding.setLifecycleOwner(this);

        binding.button2.setOnClickListener(view -> {
            Intent intent = new Intent(this,CountScoreActivity.class);
            startActivity(intent);
        });
    }
}