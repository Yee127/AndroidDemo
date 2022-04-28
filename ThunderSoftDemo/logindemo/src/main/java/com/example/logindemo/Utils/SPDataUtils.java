package com.example.logindemo.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.logindemo.bean.UserInfo;

public class SPDataUtils {
    private static final String mFileName = "my_data";

    /**
     * 保存用户信息
     * @param context  上下文信息
     * @param username  用户名
     * @return  boolean res
     */
    public static boolean saveUserInfo(Context context,
                                       String username,
                                       String password){
        boolean flag = false;
        SharedPreferences sp = context.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
        flag = true;
        return  flag;
    }

    public static UserInfo getUserInfo(Context context){
        UserInfo userInfo = null;
        SharedPreferences sp = context.getSharedPreferences(mFileName,Context.MODE_PRIVATE);
        String username = sp.getString("username", null);
        String password = sp.getString("password", null);

        userInfo = new UserInfo();
        userInfo.setPassword(password);
        userInfo.setUsername(username);
        return userInfo;
    }
}
