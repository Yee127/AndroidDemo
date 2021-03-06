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
        //        ??????
        btnOfStart.setOnClickListener(v -> {
            if (isScanning){// ????????????
                isScanning = false;
                handlerUI.sendEmptyMessage(SCANNING);
                asyncTask = new ScanTask(progressBar, mPm, scanStateText).execute();
                Log.e("??????","????????????");
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
     * ???????????????UI
     * ???????????? -> ????????????
     */
    @SuppressLint("ResourceAsColor")
    public void scanningUI(Button button,TextView textView,Message message){
        btnOfStart.setText("??? ??? ??? ???");
        btnOfStart.setBackgroundResource(R.drawable.btn_s);
//        scanStateText.setText(message.obj.toString());
        scanStateText.setTextColor(R.color.blue);
    }
    /**
     * STOP_SCANNING 0x2
     * ??????????????????UI
     * ???????????? -> ????????????
     */
    @SuppressLint("ResourceAsColor")
    public void stopScanningUI(Button button,TextView textView){
        btnOfStart.setText("??? ??? ??? ???");
        btnOfStart.setBackgroundResource(R.drawable.btn);
        scanStateText.setText("??????? ???????????????");
        scanStateText.setTextColor(R.color.red);
    }
    /**
     * CLEAN_ALL 0x3
     * ??????????????????UI
     * ????????????
     * ???????????? -> ????????????
     */
    @SuppressLint("ResourceAsColor")
    public void cleanAllUI(Button button,TextView textView){
        btnOfStart.setText("??? ??? ??? ???");
        btnOfStart.setBackgroundResource(R.drawable.btn_p);
        scanStateText.setText("???? ???????????????");
        scanStateText.setTextColor(R.color.green);
    }
    public void scanFinishUI(Button button,TextView textView){
        cacheSizeText.setText("13.2M");
        btnOfStart.setText("??? ??? ??? ???");
        btnOfStart.setBackgroundResource(R.drawable.btn);
        scanStateText.setText("???? ???????????????");
    }
    /**
     * CLEAN_OVER 0x4
     * ??????????????????UI
     * ???????????? -> ????????????
     */
    @SuppressLint("ResourceAsColor")
    public void cleanOverUI(Button button,TextView textView){
        btnOfStart.setText("??? ??? ??? ???");
        btnOfStart.setBackgroundResource(R.drawable.btn);
        scanStateText.setText("???? ???????????????");
        scanStateText.setTextColor(R.color.green);
    }

    /**
     * CLEANING  0x5
     * ????????????
     */
    @SuppressLint("ResourceAsColor")
    public void cleaning(TextView textView){
        scanStateText.setText("??????? ????????????...");
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
            long cacheSize = pStats.cacheSize; // ????????????
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
            //???????????????????????????????????????, packageInfo??????????????????????????????
            mPm = getPackageManager();
            List<PackageInfo> packages =
                    mPm.getInstalledPackages(0);
            progressBar.setMax(packages.size());
            Log.e("size",packages.size()+"");
            int progress = 0;
            //?????????????????????
            for(PackageInfo info : packages){
                //????????????
                String packageName = info.applicationInfo.loadLabel(mPm).toString();
                Log.e("??????",packageName);
                Log.e("?????????",progress+"");
                publishProgress(progress++);
                try {
                    //??????
                    Thread.sleep(200);
                    //??????????????? ??????????????????
                    Method method = mPm.getClass().
                            getMethod("getPackageSizeInfo", String.class,IPackageStatsObserver.class);
                    //????????????
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
            textView.setText("??????????? ????????????");
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
            handlerUI.sendEmptyMessage(SCAN_FINISH);
            super.onPostExecute(s);
        }
    }
}