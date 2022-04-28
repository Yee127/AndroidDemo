package com.example.studydemo.demo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jetpackstudydemo.demo1_list.ImgCard
import com.example.studydemo.demo1.ui.theme.LayoutDemo

class MainActivityOfDemo1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            com.example.studydemo.ui.theme.StudyDemoTheme {
                // A surface container using the 'background' color from the theme
//                ImgCard()
                LayoutDemo()

            }
        }
    }
}