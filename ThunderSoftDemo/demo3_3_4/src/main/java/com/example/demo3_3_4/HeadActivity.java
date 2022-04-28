package com.example.demo3_3_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class HeadActivity extends AppCompatActivity {
    private int[] imgId = {
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p8,
            R.drawable.p9
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);

        GridView gridView = findViewById(R.id.gridView);

        BaseAdapter adapter = new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if (convertView == null){
                    imageView = new ImageView(HeadActivity.this);
//                    设置图像宽高
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(150);
                    imageView.setMaxWidth(150);
//                    imageView内边距
                    imageView.setPadding(5,5,5,5);
                }else imageView = (ImageView) convertView;
                imageView.setImageResource(imgId[position]);
                return imageView;
            }

            @Override
            public int getCount() {
                return imgId.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

        };
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = getIntent();
            Bundle bundle = new Bundle();
            bundle.putInt("imgId",imgId[position]);
            intent.putExtras(bundle);
            setResult(2,intent);
            finish();
        });

    }
}