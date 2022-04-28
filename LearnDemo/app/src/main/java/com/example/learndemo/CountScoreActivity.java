package com.example.learndemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.learndemo.databinding.ActivityCountScoreBinding;

public class CountScoreActivity extends AppCompatActivity {
    CountViewModel countViewModel;
    ActivityCountScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_count_score);
        countViewModel = new ViewModelProvider(this).get(CountViewModel.class);
        binding.setData(countViewModel);
        binding.setLifecycleOwner(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        countViewModel.save();
    }
}