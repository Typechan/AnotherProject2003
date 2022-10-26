package com.example.myapplication.ui.AddEditNote

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditNoteScreen(
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.saveNote()
                    navController.navigateUp()
                },
            ) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Save note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                TextField(
                    value = titleState.title,
                    onValueChange = { note -> viewModel.enterTitle(note) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.1f),
                    label = { Text(text = "Title") },
                    maxLines = 1,
                    textStyle = TextStyle(fontSize = 30.sp)
                )
                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp))
                TextField(
                    value = contentState.text,
                    onValueChange = { note -> viewModel.enterNote(note) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
        }
    }
}