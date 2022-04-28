package com.example.studydemo.state.todoDemo.a

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studydemo.state.todoDemo.a.ui.theme.StudyDemoTheme

class ToDoMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyDemoTheme {
                // A surface container using the 'background' color from the theme
                TodoActivityScreen()
            }
        }
    }

    @Composable
    private fun TodoActivityScreen(){
        val items = listOf(
            TodoItem("Learn",TodoIcon.Event),
            TodoItem("A",TodoIcon.Event),
            TodoItem("B",TodoIcon.Event),
            TodoItem("C",TodoIcon.Event),
            TodoItem("D",TodoIcon.Trash),
            TodoItem("E",TodoIcon.Square),
            TodoItem("F",TodoIcon.Done),
        )
        
        TodoScreen(items = items)
    }
}

