package com.example.twowaybinding.way2;

import android.util.Log;

import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.twowaybinding.User;

public class UserViewModel2 {

    private ObservableField<User> userObservableField;

    public UserViewModel2() {
        User user = new User("Hello");

        userObservableField = new ObservableField<>();
        userObservableField.set(user);
    }


    public String getUserName(){
        Log.e("tag","GET===>"+userObservableField.get().getUsername());
        return userObservableField.get().getUsername();
    }

    public void setUserName(String userName){
        Log.e("tag",userName);
        userObservableField.get().setUsername(userName);
    }
}
