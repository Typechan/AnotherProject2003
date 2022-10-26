package com.example.myapplication.ui.mainscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import com.example.myapplication.data.NoteEntity
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun NoteItem(
    noteEntity: NoteEntity,
    height: Int = 50,
    modifier: Modifier,
    onDeleteClick: ()->Unit
){
    Box(modifier = modifier
        .height(height.dp)){
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize(0.85f)) {
                Text(text = noteEntity.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = noteEntity.note, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = noteEntity.creationDate, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            Button(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete note",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }

    }
}