package com.example.jetpackofkotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackofkotlin.MyViewModel.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var num:Int = 0
    lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        viewModel.num.observe(this, Observer {
            tv1.text = it.toString()
        })
        tv1.text = "0"
    }

    fun increase(view: View) {
        viewModel.modifyNum(1)
    }
    fun decrease(view: View) {
        viewModel.modifyNum(-1)
    }

}