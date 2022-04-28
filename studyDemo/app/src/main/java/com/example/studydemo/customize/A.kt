package com.example.studydemo.customize

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.studydemo.ui.theme.StudyDemoTheme
import java.time.Year


fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
)=this.then(
    layout{
        measurable, constraints ->

//        测量元素
        val placeable = measurable.measure(constraints)
//        测量之后获取元素的基线值
        val firstBaseline = placeable[FirstBaseline]
//        元素新的Y坐标  =  新基线 - 旧基线
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(width = placeable.width, height = height){
            placeable.placeRelative(0,placeableY)
        }
    }

)

@Composable
fun TextWithPaddingToBaseline(){

    StudyDemoTheme {
        Text(text = "hahahahahhahaha",
            Modifier
                .firstBaselineToTop(35.dp)
                .background(Color.Red))
    }
}


