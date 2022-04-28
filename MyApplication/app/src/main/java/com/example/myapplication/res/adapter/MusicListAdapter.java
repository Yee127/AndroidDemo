package com.example.myapplication.res.adapter;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.res.entity.Song;
import com.example.myapplication.utils.MusicUtils;

import java.io.InputStream;
import java.util.List;

public class MusicListAdapter extends BaseQuickAdapter<Song, BaseViewHolder> {

    public MusicListAdapter(int layoutResId, @Nullable List<Song> data) {
        super(layoutResId, data);

    }
    @Override
    protected void convert(BaseViewHolder helper, Song item) {
        //给控件赋值
        int duration = item.duration;
        String time = MusicUtils.formatTime(duration);
        Log.e("loaclDuration","++++++++++++++++++>"+duration);

        String album_id = item.album_art;

        Bitmap artistImage = getArtistImage(album_id);
        BitmapDrawable bmpDraw = new BitmapDrawable(artistImage);
//                    mImageView.setImageDrawable(bmpDraw);
        helper.setText(R.id.tv_song_name,item.getSong().trim())//歌曲名称
                .setText(R.id.tv_singer,item.getSinger())//歌手
                .setText(R.id.tv_duration_time,time)
                .setImageDrawable(R.id.civ_albumImg,bmpDraw)//歌曲专辑图片
                //歌曲序号，因为getAdapterPosition得到的位置是从0开始，故而加1，
                //是因为位置和1都是整数类型，直接赋值给TextView会报错，故而拼接了""
                .setText(R.id.tv_position,helper.getAdapterPosition()+1+"");
        helper.addOnClickListener(R.id.item_music);//给item添加点击事件，点击之后传递数据到播放页面或者在本页面进行音乐播放

    }

    public Bitmap getArtistImage(String albumid) {
        Bitmap artwork = null;
        try {
            Uri sArtworkUri = Uri
                    .parse("content://media/external/audio/albumart");
            Uri uri = ContentUris.withAppendedId(sArtworkUri,
                    Long.valueOf(albumid));
            ContentResolver res = mContext.getContentResolver();
            InputStream in = res.openInputStream(uri);
            artwork = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        return artwork;
    }
}
