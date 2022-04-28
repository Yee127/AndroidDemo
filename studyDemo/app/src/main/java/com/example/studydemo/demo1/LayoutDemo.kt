package com.example.studydemo.demo1.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import com.example.jetpackstudydemo.demo1_list.ImgCard
import com.example.studydemo.demo1.LazyListDemo
import com.example.studydemo.demo1.ListDemo

@Composable
fun LayoutDemo(){
  Scaffold(
      topBar = {
          TopAppBar(
              title = { Text(text = "Layout") },
              actions = {
                  IconButton(onClick = { /*TODO*/ }) {
                      Icon(imageVector = Icons.Filled.Favorite, contentDescription = "like")
                  }
              }
          )
      }
  ) {
//          innerPadding ->
//      ImgCard(Modifier.padding(innerPadding))
      Column() {
          ImgCard()
          Row {
              ListDemo()

              LazyListDemo()
          }
      }


  }
}

