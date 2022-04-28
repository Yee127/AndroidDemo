package com.example.myapplication.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.myapplication.res.entity.Song;
import com.example.myapplication.res.netSongs.NetSongs;

import java.util.ArrayList;
import java.util.List;

public class NetMusicUtils {
    /**
     * 扫描系统里面的音频文件，返回一个list集合
     * @param context
     */
    public static List<Song> getMusicData(Context context) {
        NetSongs netSongs = new NetSongs();

        List<Song> list = netSongs.getNetSongsList();
        return list;
    }

    /**
     * 定义一个方法用来格式化获取到的时间
     */
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;

        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }

    }
}

