package com.example.studydemo.state.todoDemo.a

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.*

data class TodoItem(
    val task:String,
    val icon: TodoIcon = TodoIcon.Default,
    val id: UUID = UUID.randomUUID()
) {

}

enum class TodoIcon(val imgVevtor: ImageVector,
                    @SuppressLint("SupportAnnotationUsage") @StringRes val contentDescription:String){
    Square(Icons.Default.CropSquare,"Expand"),
    Done(Icons.Default.Done,"Done"),
    Event(Icons.Default.Event,"Event"),
    Privacy(Icons.Default.PrivacyTip,"Privacy"),
    Trash(Icons.Default.RestoreFromTrash,"Trash");

    companion object {
        val Default = Square
    }

}