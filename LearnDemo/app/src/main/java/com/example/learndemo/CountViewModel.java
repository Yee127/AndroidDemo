package com.example.learndemo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class CountViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> num_red;
    private MutableLiveData<Integer> num_blue;

    private Integer redUndo,blueUndo;
    SavedStateHandle handle;

//    final static String KEY =
    String KEY_B = getApplication().getResources().getString(R.string.key_blue);
    String KEY_R = getApplication().getResources().getString(R.string.key_red);
    String MY_DATA = getApplication().getResources().getString(R.string.my_data);
    public CountViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if (!(handle.contains(KEY_B) && handle.contains(KEY_R))){
            load();
        }
    }

//    public LiveData<Integer> getScoreB(){
//        return handle.getLiveData(KEY_B);
//    }
//    public LiveData<Integer> getScoreR(){
//        return handle.getLiveData(KEY_R);
//    }
    public void load() {
        SharedPreferences shp = getApplication().getSharedPreferences(MY_DATA, Context.MODE_PRIVATE);
        int numB = shp.getInt(KEY_B,0);
        int numR = shp.getInt(KEY_R,0);
        handle.set(KEY_B,numB);
        handle.set(KEY_R,numR);

    }
    public void save(){
        SharedPreferences shp = getApplication().getSharedPreferences(MY_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(KEY_B,getNum_blue().getValue());
        editor.putInt(KEY_R,getNum_red().getValue());
        editor.apply();
    }

    public MutableLiveData<Integer> getNum_red() {
        if (num_red == null){
            num_red = new MutableLiveData<>();
            num_red.setValue(0);
        }
        return num_red;
    }

    public void setNum_red(MutableLiveData<Integer> num_red) {
        this.num_red = num_red;
    }

    public MutableLiveData<Integer> getNum_blue() {
        if (num_blue == null){
            num_blue = new MutableLiveData<>();
            num_blue.setValue(0);
        }
        return num_blue;
    }

    public void setNum_blue(MutableLiveData<Integer> num_blue) {
        this.num_blue = num_blue;
    }

    public void add1(MutableLiveData<Integer> num){
        saveNum();
        num.setValue(num.getValue()+1);
    }

    public void add2(MutableLiveData<Integer> num){
        saveNum();
        num.setValue(num.getValue()+2);
    }

    public void add3(MutableLiveData<Integer> num){
        saveNum();
        num.setValue(num.getValue()+3);
    }

    public void undo(){
        num_red.setValue(redUndo);
        num_blue.setValue(blueUndo);
    }
    // 保存每一步的值
    private void saveNum(){
        redUndo = num_red.getValue();
        blueUndo = num_blue.getValue();
        handle.set(KEY_R,getNum_red().getValue());
        handle.set(KEY_B,getNum_blue().getValue());
    }

    // 重置
    public void reset(){
        saveNum();
        num_blue.setValue(0);
        num_red.setValue(0);
    }


}
