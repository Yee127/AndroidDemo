package com.example.myapplication.res.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.res.entity.Song;
import com.example.myapplication.utils.NetMusicUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class NetMusicListAdapter extends BaseQuickAdapter<Song, BaseViewHolder> {
    ImageView album;
    Bitmap artistImage;
    String songUrl;
    android.os.Handler handler;
    public NetMusicListAdapter(int layoutResId, @Nullable List<Song> data) {
        super(layoutResId, data);

    }
    @Override
    protected void convert(BaseViewHolder helper, Song item) {
        //给控件赋值
        int duration = item.duration;
        String time = NetMusicUtils.formatTime(duration);
        //  专辑图片
        songUrl = item.albumPath;
        loadImg(songUrl);
        helper.setText(R.id.tv_song_name,item.getSong())//歌曲名称
                .setText(R.id.tv_singer,item.getSinger())//歌手
                .setText(R.id.tv_duration_time,time)
//                .setImageBitmap(R.id.civ_albumImg,artistImage)//歌曲专辑图片
                .setText(R.id.tv_position,helper.getAdapterPosition()+1+"");

        // 处理显示图片
        handler = new Handler((msg) ->{
            if(msg.what == 1){
                Bitmap obj = (Bitmap) msg.obj;
                helper.setImageBitmap(R.id.civ_albumImg,obj);
            }
            return false;
        }
        );
        helper.addOnClickListener(R.id.item_music);//给item添加点击事件，点击之后传递数据到播放页面或者在本页面进行音乐播放

    }

    public void loadImg(String url){
        new Thread(()->{
            try {
                artistImage = Picasso.get().load(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Message message = new Message();
            message.what = 1;
            message.obj = artistImage;
            handler.sendMessage(message);
            Log.e("artistImage","+++++++++++++++++++>"+artistImage);
        }).start();

    }



    //加载图片
    public Bitmap getURLimage(String url){
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);//读取图像数据
            Log.e("bitmap","===========================>"+bmp);
            //读取文本数据
            //byte[] buffer = new byte[100];
            //inputStream.read(buffer);
            //text = new String(buffer);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }
}
