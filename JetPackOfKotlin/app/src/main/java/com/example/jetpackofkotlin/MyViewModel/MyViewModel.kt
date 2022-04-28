package com.example.jetpackofkotlin.MyViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _num:MutableLiveData<Int> by lazy { MutableLiveData<Int>().also { it.value = 0 } }

    val num: LiveData<Int>
        get() = _num
        
    fun modifyNum(number:Int){
        _num.value = _num.value?.plus(number)
    }
    /**
     * 初始化 way1
     * way2 lazy...
     */
//    init {
//        num = MutableLiveData()
//        num.value = 0
//    }

}