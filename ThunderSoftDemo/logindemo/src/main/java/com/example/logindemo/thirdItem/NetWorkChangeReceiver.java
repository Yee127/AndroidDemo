package com.example.logindemo.thirdItem;



//import static androidx.core.content.ContextCompat.getSystemService;

import static androidx.core.content.ContextCompat.getSystemService;

import android.net.ConnectivityManager;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetWorkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            Toast.makeText(context, "网络已连接！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "当前网络不可用！", Toast.LENGTH_SHORT).show();
        }
    }
}
