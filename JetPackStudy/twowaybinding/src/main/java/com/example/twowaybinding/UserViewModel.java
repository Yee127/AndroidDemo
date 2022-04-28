package com.example.twowaybinding;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class UserViewModel extends BaseObservable {

    private User user;

    public UserViewModel() {
        this.user = new User("Jack");
    }
    @Bindable
    public String getUserName(){
        return  user.getUsername();
    }

    public void setUserName(String username){

        if (username != null && !username.equals(user.getUsername()))
        {
            user.setUsername(username);
            Log.e("tag","set name"+ username);
            notifyPropertyChanged(BR.userName);
        }
    }
}
