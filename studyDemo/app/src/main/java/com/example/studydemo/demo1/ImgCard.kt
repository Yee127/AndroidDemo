package com.example.jetpackstudydemo.demo1_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studydemo.R

@Preview
@Composable
fun ImgCard(modifier: Modifier = Modifier){
    Row() {
        Surface(modifier = Modifier.size(50.dp),
        shape = CircleShape,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)) {
            Image(painter = painterResource(id = R.drawable.img1), contentDescription = null,
            contentScale = ContentScale.FillHeight)
        }

        
        Column(
            modifier = Modifier.padding(start = 10.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "Test",
                fontWeight = FontWeight.Bold,

            )
            Text(text = "1 minutes age",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )
        }
    }

}