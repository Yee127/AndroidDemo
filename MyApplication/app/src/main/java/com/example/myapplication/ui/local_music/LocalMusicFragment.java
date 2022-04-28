package com.example.myapplication.ui.local_music;

import static com.example.myapplication.utils.DateUtil.parseTime;

import static java.util.Arrays.asList;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLocalBinding;
import com.example.myapplication.res.adapter.MusicListAdapter;
import com.example.myapplication.res.entity.Song;
import com.example.myapplication.utils.Constant;
import com.example.myapplication.utils.MusicUtils;
import com.example.myapplication.utils.ObjectUtils;
import com.example.myapplication.utils.SPUtils;
import com.example.myapplication.utils.ToastUtils;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ExplainReasonCallbackWithBeforeParam;
import com.permissionx.guolindev.callback.ForwardToSettingsCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ExplainScope;
import com.permissionx.guolindev.request.ForwardScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocalMusicFragment extends Fragment implements MediaPlayer.OnCompletionListener{
    private MusicListAdapter mAdapter;//歌曲适配器
    private List<Song> mList;//歌曲列表
    private MediaPlayer mediaPlayer;//音频播放器
    private String musicData = null;
    // 记录当前播放歌曲的位置
    public int mCurrentPosition = 0 ;
    RecyclerView rvMusic;
    Button btnScan;
    LinearLayout scanLay;
    private TextView name;
    private TextView singer;
    private SeekBar timeSeekBar;
    private TextView tvPlayTime;
    private TextView tvTotalTime;
    private ImageButton btnPre;
    private ImageButton btnStart;
    private ImageButton btnNext;
    private FragmentLocalBinding mBinding;
    private ImageView album;
    private ImageButton btnPlayMode;
    private static String playModeState = "cycle";
    private static final int INTERNAL_TIME = 1000;// 音乐进度间隔时间
    private List<String> playMode = asList("random","cycle","single");
    private int playModeIndex = 0;
    Random r = new Random();
    private Handler mHandler = new Handler(message -> {
        // 展示给进度条和当前时间
        int progress = mediaPlayer.getCurrentPosition();
        timeSeekBar.setProgress(progress);
        tvPlayTime.setText(parseTime(progress));
        // 继续定时发送数据
        updateProgress();
        return true;
    });
    private void updateProgress() {
        // 使用Handler每间隔1s发送一次空消息，通知进度条更新
        Message msg = Message.obtain();// 获取一个现成的消息
        // 使用MediaPlayer获取当前播放时间除以总时间的进度
        int progress = mediaPlayer.getCurrentPosition();
        msg.arg1 = progress;
        mHandler.sendMessageDelayed(msg, INTERNAL_TIME);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void setPlayModeState(String playModeState) {
        LocalMusicFragment.playModeState = playModeState;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        musicData = SPUtils.getString(Constant.MUSIC_DATA_FIRST, "yes", getContext());
        mBinding = FragmentLocalBinding.inflate(inflater);
        View root = mBinding.getRoot();
        rvMusic = root.findViewById(R.id.rv_music);
        btnScan = root.findViewById(R.id.btn_scan);
        scanLay = root.findViewById(R.id.scan_lay);
        btnStart = root.findViewById(R.id.btn_play_or_pause);
        btnPre = root.findViewById(R.id.btn_pre);
        btnNext = root.findViewById(R.id.btn_next);
        name = root.findViewById(R.id.tv_song_name_dk);
        singer = root.findViewById(R.id.tv_singer_name_dk);
        tvTotalTime = root.findViewById(R.id.tv_total_time);
        tvPlayTime =root.findViewById(R.id.tv_play_time);
        timeSeekBar = root.findViewById(R.id.time_seekBar);
        album = root.findViewById(R.id.iv_album_img_dk);
        btnPlayMode = root.findViewById(R.id.play_mode);
        timeSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);//滑动条监听
        btnScan.setOnClickListener(view -> {
            // 扫描本地歌曲
            permissionsRequest();
        });
        btnStart.setOnClickListener(view -> {
            // 首次点击播放按钮，默认播放第0首，下标从0开始
            if (mediaPlayer == null) {
                changeMusic(0);
            } else {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnStart.setBackgroundResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    btnStart.setBackgroundResource(R.drawable.pause);
                }
            }
        });
        btnPre.setOnClickListener(view -> {
            int randomIndex = r.nextInt(mList.size());
            if (this.playModeState.equals("random")){
                changeMusic(randomIndex);
            }else {
                changeMusic(--mCurrentPosition);
            }
        });
        btnNext.setOnClickListener(view -> {
            int randomIndex = r.nextInt(mList.size());
            if (this.playModeState.equals("random")){
                changeMusic(randomIndex);
            }else {
                changeMusic(++mCurrentPosition);
            }
        });

        btnPlayMode.setOnClickListener(view -> {
            // 切换播放模式
            setPlayModeState(playMode.get((playModeIndex++)%playMode.size()));
            if (playModeState.equals("cycle"))
            {
                btnPlayMode.setBackgroundResource(R.drawable.cycle);
                Toast.makeText(getContext(), "列表播放", Toast.LENGTH_SHORT).show();
            }else if(playModeState.equals("single") ){
                btnPlayMode.setBackgroundResource(R.drawable.single);
                Toast.makeText(getContext(), "单曲循环", Toast.LENGTH_SHORT).show();
            }else {
                btnPlayMode.setBackgroundResource(R.drawable.random);
                Toast.makeText(getContext(), "随机播放", Toast.LENGTH_SHORT).show();
            }

        });
        if (musicData.equals("no")) {//说明是第一次打开APP，未进行扫描
            scanLay.setVisibility(View.GONE);
            initMusic();
        } else {
            scanLay.setVisibility(View.VISIBLE);
        }

        return root;
    }

    /**
     * 动态权限请求
     */
    private void permissionsRequest() {
        PermissionX.init(this).permissions(
                //写入文件
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onExplainRequestReason(new ExplainReasonCallbackWithBeforeParam() {
                    @Override
                    public void onExplainReason(ExplainScope scope, List<String> deniedList, boolean beforeRequest) {
                        scope.showRequestReasonDialog(deniedList, "即将申请的权限是程序必须依赖的权限", "我已明白");
                    }
                })
                .onForwardToSettings(new ForwardToSettingsCallback() {
                    @Override
                    public void onForwardToSettings(ForwardScope scope, List<String> deniedList) {
                        scope.showForwardToSettingsDialog(deniedList, "您需要去应用程序设置当中手动开启权限", "我已明白");
                    }
                })
                .setDialogTintColor(R.color.white, R.color.all_color)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                        if (allGranted) {
                            //通过后的业务逻辑
                            initMusic();
                        } else {
                            Toast.makeText(getContext(), "您拒绝了如下权限：" + deniedList, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

//    获取音乐列表
    private void initMusic() {
        mList = new ArrayList<>();//实例化
        //数据赋值
        mList = MusicUtils.getMusicData(getContext());//将扫描到的音乐赋值给音乐列表
        if (!ObjectUtils.isEmpty(mList) && mList != null) {
            scanLay.setVisibility(View.GONE);
            SPUtils.putString(Constant.MUSIC_DATA_FIRST, "no", getContext());
        }
        mAdapter = new MusicListAdapter(R.layout.song_item, mList);//指定适配器的布局和数据源
        //线性布局管理器，可以设置横向还是纵向，RecyclerView默认是纵向的，所以不用处理,如果不需要设置方向，代码还可以更加的精简如下
        rvMusic.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置适配器
        rvMusic.setAdapter(mAdapter);
        //item的点击事件
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.item_music) {
                mCurrentPosition = position;
                changeMusic(mCurrentPosition);
                Log.e("mList","=====>"+ mList.get(mCurrentPosition).singer);
//                    mAdapter.notifyItemRangeChanged(0, mList.size());
            }
        });
    }
    //滑动条监听
    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int progress = seekBar.getProgress();
            mediaPlayer.seekTo(progress);
        }
    };
    //    切歌
    private void changeMusic(int position) {
        Log.e("MainActivity", "position:" + position);
        if (position < 0) {
            mCurrentPosition = position = mList.size() - 1;
            Log.e("MainActivity", "mList.size:" + mList.size());
        } else if (position > mList.size() - 1) {
            mCurrentPosition = position = 0;
        }
        Log.e("MainActivity", "position:" + position);
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnCompletionListener(this);//监听音乐播放完毕事件，自动下一曲
        }
        try {
            // 切歌之前先重置，释放掉之前的资源
            mediaPlayer.reset();
            // 设置播放源
            Log.d("Music", mList.get(position).path);
            mediaPlayer.setDataSource(mList.get(position).path);
            name.setText(mList.get(position).song);
            singer.setText(mList.get(position).singer);
            String album_art = mList.get(position).album_art;
            Bitmap artistImage = mAdapter.getArtistImage(album_art);
            BitmapDrawable bmpDraw = new BitmapDrawable(artistImage);
            album.setImageDrawable(bmpDraw);
            // 开始播放前的准备工作，加载多媒体资源，获取相关信息
            mediaPlayer.prepare();
            // 开始播放
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 切歌时重置进度条并展示歌曲时长
        timeSeekBar.setProgress(0);
        timeSeekBar.setMax(mediaPlayer.getDuration());
        tvTotalTime.setText(parseTime(mediaPlayer.getDuration()));
        updateProgress();
        if (mediaPlayer.isPlaying()) {
            btnStart.setBackgroundResource(R.drawable.pause);
        } else {
            btnStart.setBackgroundResource(R.drawable.play);
        }
    }
    //    播放完成之后自动下一曲
    @Override
    public void onCompletion(MediaPlayer mp) {
        int randomIndex = r.nextInt(mList.size());
        if (this.playModeState.equals("random")){
            // 随机播放
            changeMusic(randomIndex);
        }else if(this.playModeState.equals("single")){
            // 单曲循环
            changeMusic(mCurrentPosition);
        }else {
            // 顺序播放
            changeMusic(++mCurrentPosition);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}