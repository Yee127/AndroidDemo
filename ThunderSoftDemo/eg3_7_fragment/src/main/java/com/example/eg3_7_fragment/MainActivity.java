package com.example.eg3_7_fragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public class DetailActivity extends Activity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
//            判断是否为横屏，为横屏就结束当前activity 使用fragment显示
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                finish();
                return;
            }
//            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//                onCreate(savedInstanceState);
//                return;
//            }

            if (savedInstanceState == null){
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(getIntent().getExtras());
                getFragmentManager().beginTransaction().add(android.R.id.content,detailFragment).commit();
            }

        }
    }
}