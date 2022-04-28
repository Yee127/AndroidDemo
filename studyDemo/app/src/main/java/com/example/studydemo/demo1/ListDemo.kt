package com.example.studydemo.demo1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ListDemo(modifier: Modifier = Modifier){
    val scrollState = rememberScrollState()
    
    Column(Modifier.verticalScroll(scrollState)) {
        repeat(1000){
            Text(text = "item ==> $it")
        }
        
    }
}

@Composable
fun LazyListDemo(modifier: Modifier = Modifier){
    val scrollState = rememberLazyListState()

    LazyColumn() {
        items(1000){
            Text(text = "item of lazy==> $it")
        }
    }
}