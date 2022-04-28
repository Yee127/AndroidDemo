package com.example.logindemo.seventhItem;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logindemo.R;
import com.example.logindemo.seventhItem.entity.CacheInfo;

import java.lang.reflect.Method;
import java.util.List;

public class SeventhItemActivity extends AppCompatActivity {
    private AsyncTask<String, Integer, String> asyncTask;
    private final static int SCANNING = 0x1;
    private final static int STOP_SCANNING = 0x2;
    private final static int SCAN_FINISH = 0x3;

    private ProgressBar progressBar;
    private Button btnOfStart;
    private TextView scanStateText;
    private TextView cacheSizeText;
    private PackageManager mPm;
    private boolean isScanning = true;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh_item);
        initUI();
        clickBtn(btnOfStart,scanStateText,progressBar);
    }
    private void initUI(){
        btnOfStart = findViewById(R.id.start_scan_Btn);
        scanStateText = findViewById(R.id.cur_state_text);
        cacheSizeText = findViewById(R.id.number_of_garbage_files_text);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
    }

    /**
     *
     * @param btnOfStart
     * @param scanStateText
     */
    private void clickBtn(Button btnOfStart, TextView scanStateText,ProgressBar progressBar) {
        //        扫描
        btnOfStart.setOnClickListener(v -> {
            if (isScanning){// 正在扫描
                isScanning = false;
                handlerUI.sendEmptyMessage(SCANNING);
                asyncTask = new ScanTask(progressBar, mPm, scanStateText).execute();
                Log.e("提示","扫描完成");
            }else{
                isScanning = true;
                asyncTask.cancel(false);
                progressBar.setVisibility(View.GONE);
                handlerUI.sendEmptyMessage(STOP_SCANNING);
            }
        });

    }

    /**
     * SCANNING 0x1
     * 设置扫描中UI
     * 开始扫描 -> 终止扫描
     */
    @SuppressLint("ResourceAsColor")
    public void scanningUI(Button button,TextView textView,Message message){
        btnOfStart.setText("终 止 扫 描");
        btnOfStart.setBackgroundResource(R.drawable.btn_s);
//        scanStateText.setText(message.obj.toString());
        scanStateText.setTextColor(R.color.blue);
    }
    /**
     * STOP_SCANNING 0x2
     * 设置终止扫描UI
     * 终止扫描 -> 开始扫描
     */
    @SuppressLint("ResourceAsColor")
    public void stopScanningUI(Button button,TextView textView){
        btnOfStart.setText("开 始 扫 描");
        btnOfStart.setBackgroundResource(R.drawable.btn);
        scanStateText.setText("🥶️ 扫描终止！");
        scanStateText.setTextColor(R.color.red);
    }
    /**
     * CLEAN_ALL 0x3
     * 设置一键清除UI
     * 扫描完成
     * 终止扫描 -> 一键清除
     */
    @SuppressLint("ResourceAsColor")
    public void cleanAllUI(Button button,TextView textView){
        btnOfStart.setText("一 键 清 除");
        btnOfStart.setBackgroundResource(R.drawable.btn_p);
        scanStateText.setText("🧐 扫描完成！");
        scanStateText.setTextColor(R.color.green);
    }
    public void scanFinishUI(Button button,TextView textView){
        cacheSizeText.setText("13.2M");
        btnOfStart.setText("重 新 扫 描");
        btnOfStart.setBackgroundResource(R.drawable.btn);
        scanStateText.setText("🧐 扫描完成！");
    }
    /**
     * CLEAN_OVER 0x4
     * 设置清除完成UI
     * 一键清除 -> 开始扫描
     */
    @SuppressLint("ResourceAsColor")
    public void cleanOverUI(Button button,TextView textView){
        btnOfStart.setText("开 始 扫 描");
        btnOfStart.setBackgroundResource(R.drawable.btn);
        scanStateText.setText("😝 清除完成！");
        scanStateText.setTextColor(R.color.green);
    }

    /**
     * CLEANING  0x5
     * 正在清除
     */
    @SuppressLint("ResourceAsColor")
    public void cleaning(TextView textView){
        scanStateText.setText("🤯️ 正在清除...");
        scanStateText.setTextColor(R.color.green);
    }



    @SuppressLint("ResourceAsColor")
    Handler handlerUI = new Handler(msg -> {
        switch (msg.what){
            case SCANNING:
                scanningUI(btnOfStart,scanStateText,msg);
                break;
            case STOP_SCANNING:
                stopScanningUI(btnOfStart,scanStateText);
                break;
            case SCAN_FINISH:
                scanFinishUI(btnOfStart,scanStateText);
                break;
        }
       return false;
    });


    class MyStatsObserver extends IPackageStatsObserver.Stub{
        @Override
        public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
            long cacheSize = pStats.cacheSize; // 缓存大小
            if (cacheSize >0){
                String packageName = pStats.packageName;
                try {
                    ApplicationInfo applicationInfo = mPm.getApplicationInfo(packageName, 0);
                    CacheInfo cacheInfo = new CacheInfo();
                    cacheInfo.setName(applicationInfo.loadLabel(mPm).toString());
                    cacheInfo.setIcon(applicationInfo.loadIcon(mPm));
                    cacheInfo.setCacheSize(cacheSize);
                    Log.e("---> CacheSize",""+cacheSize);
                    cacheInfo.setPackageName(packageName);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private  class ScanTask extends AsyncTask<String,Integer,String>{
        private ProgressBar progressBar;
        private PackageManager mPm;
        private TextView textView;

        public ScanTask(ProgressBar progressBar, PackageManager mPm, TextView textView) {
            this.progressBar = progressBar;
            this.mPm = mPm;
            this.textView = textView;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            //获取已安装的应用程序的信息, packageInfo存储包的整体内容信息
            mPm = getPackageManager();
            List<PackageInfo> packages =
                    mPm.getInstalledPackages(0);
            progressBar.setMax(packages.size());
            Log.e("size",packages.size()+"");
            int progress = 0;
            //遍历每个包信息
            for(PackageInfo info : packages){
                //应用名称
                String packageName = info.applicationInfo.loadLabel(mPm).toString();
                Log.e("包名",packageName);
                Log.e("进度条",progress+"");
                publishProgress(progress++);
                try {
                    //休眠
                    Thread.sleep(200);
                    //通过反射， 获取缓存大小
                    Method method = mPm.getClass().
                            getMethod("getPackageSizeInfo", String.class,IPackageStatsObserver.class);
                    //调用方法
                    method.invoke(mPm, packageName, new MyStatsObserver());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setMax(68);
            progressBar.setProgress(values[0]);
            textView.setText("😶‍🌫 正在扫描");
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
            handlerUI.sendEmptyMessage(SCAN_FINISH);
            super.onPostExecute(s);
        }
    }
}