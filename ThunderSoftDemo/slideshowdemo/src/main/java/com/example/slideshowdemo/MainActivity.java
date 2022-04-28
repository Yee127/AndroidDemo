package com.example.slideshowdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private int imgId[] = {R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i};
    private ImageSwitcher imageSwitcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gallery gallery = findViewById(R.id.gallery);
        imageSwitcher =findViewById(R.id.imgSwitch);
//        淡入动画
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
//        淡出动画
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));
        imageSwitcher.setFactory(() ->{
             ImageView imageView = new ImageView(MainActivity.this);
             imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);//纵横比居中缩放
             imageView.setLayoutParams(new FrameLayout.LayoutParams(
                     FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
             return imageView;
        });
        BaseAdapter myAdapter = new BaseAdapter(){
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
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if (convertView == null){
                    imageView = new ImageView(MainActivity.this);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setLayoutParams(new Gallery.LayoutParams(180,135));
                    //设置gallery每一项图片的背景资源(使用的是attr.xml的自定义样式)
                    TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
                    imageView.setBackgroundResource(typedArray.getResourceId(
                            R.styleable.Gallery_android_galleryItemBackground,0));
                    imageView.setPadding(5,0,5,0);
                }else {
                    imageView = (ImageView) convertView;
                }
                imageView.setImageResource(imgId[position]);
                return imageView;
            }
        };

        gallery.setAdapter(myAdapter);
        gallery.setSelection(imgId.length/2);
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageSwitcher.setImageResource(imgId[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}