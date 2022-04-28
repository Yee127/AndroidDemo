package com.example.studydemo.state.todoDemo.a

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TodoScreen(items:List<TodoItem>){
    Column() {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ){
            items(items){
                TodoRow( todo = it ,
                modifier = Modifier.fillMaxWidth())
            }
        }
        
        Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Text(text = "Add")
        }
    }
}

@Composable
fun TodoRow(
    todo:TodoItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween  // 子元素水平均匀分布
    ) {
        Text(text = todo.task)

        Icon(imageVector = todo.icon.imgVevtor, contentDescription = "")

    }
}

