package com.example.learndemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> num;

    public MutableLiveData<Integer> getNum() {
        if (num == null){
            num = new MutableLiveData<>();
            num.setValue(0 );
        }
        return num;
    }

    public void setNum(MutableLiveData<Integer> num) {
        this.num = num;
    }

    public void add(){
        num.setValue(num.getValue()+1);
    }
}
