package com.example.studydemo.customize

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import com.example.studydemo.ui.theme.StudyDemoTheme

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content:@Composable ()-> Unit){
    Layout(
        content = content,
        modifier = Modifier){ measurables, constraints ->
        measurables.map { measurable ->
            measurable.measure(constraints)
        }
        layout(0,0){

        }

    }
}


@Composable
fun OwnColumnDemo(modifier:Modifier = Modifier){
   StudyDemoTheme() {
       MyOwnColumn() {
           Text(text = "lalalalal")
           Text(text = "lalalalal")
           Text(text = "lalalalal")
           Text(text = "lalalalal")
           Text(text = "lalalalal")
       }
   }
}