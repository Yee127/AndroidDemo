package com.example.logindemo.bean;

import android.content.Context;

import com.example.logindemo.R;

public class SettingItemBean {


    public static final String [] TITLES = {
        "蜂窝网络",
        "无线局域网",
        "飞行模式",
        "更多设置",
        "用户隐私",
        "亮度调节",
        "描述文件",
        "系统信息"
    };

    public static final Integer [] ICONS = {
      R.drawable.net,
      R.drawable.wifi,
      R.drawable.air,
      R.drawable.funcsetting,
      R.drawable.secret,
      R.drawable.light,
      R.drawable.allow,
      R.drawable.setting,
    };

    public static String[] getTITLES() {
        return TITLES;
    }

    public static Integer[] getICONS() {
        return ICONS;
    }
}
