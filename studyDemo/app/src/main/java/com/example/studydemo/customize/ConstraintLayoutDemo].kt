package com.example.studydemo.customize

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun ConstraintLayoutContent(){
    ConstraintLayout {
//        一次性赋值
        val (button, text) = createRefs()
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button){
                top.linkTo(parent.top, margin = 15.dp)
            }
        )
        {
            Text(text = "Button")
        }

        Text(text = "djfkdjfkd", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 15.dp)
            centerHorizontallyTo(parent)
        })
    }
}