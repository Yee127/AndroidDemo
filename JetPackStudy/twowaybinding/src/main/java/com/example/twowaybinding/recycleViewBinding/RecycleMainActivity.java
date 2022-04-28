package com.example.twowaybinding.recycleViewBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.twowaybinding.R;
import com.example.twowaybinding.databinding.ActivityRecycleMainBinding;

public class RecycleMainActivity extends AppCompatActivity {

    private ActivityRecycleMainBinding activityRecycleMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRecycleMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_recycle_main);
        activityRecycleMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecycleViewAdapter adapter = new RecycleViewAdapter(IdolUtils.get());
        activityRecycleMainBinding.recyclerView.setAdapter(adapter);
    }
}