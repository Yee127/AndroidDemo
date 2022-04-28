package com.example.myapplication.res.entity;

import androidx.databinding.BaseObservable;

public class Song extends BaseObservable {
    public String albumPath;

    public Song() {
    }

    public Song(  String song, String singer,String path,String albumPath,int duration) {
        this.albumPath = albumPath;
        this.singer = singer;
        this.song = song;
        this.path = path;
        this.duration = duration;
    }

    /**
     * 歌手
     */
    public String singer;
    /**
     * 歌曲名
     */
    public String song;
    /**
     * 专辑名
     */
    public String album;
    /**
     * 专辑图片
     */
    public String album_art;
    /**
     * 歌曲的地址
     */
    public String path;
    /**
     * 歌曲长度
     */
    public int duration;
    /**
     * 歌曲的大小
     */
    public long size;

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbum_art() {
        return album_art;
    }

    public void setAlbum_art(String album_art) {
        this.album_art = album_art;
    }

    @Override
    public String toString() {
        return "Song{" +
                "singer='" + singer + '\'' +
                ", song='" + song + '\'' +
                ", album='" + album + '\'' +
                ", album_art='" + album_art + '\'' +
                ", path='" + path + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                '}';
    }
}
