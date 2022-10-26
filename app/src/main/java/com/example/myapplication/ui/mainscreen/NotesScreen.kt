package com.example.myapplication.ui.mainscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.mainscreen.components.NoteItem
import com.example.myapplication.ui.AddEditNote.AddEditNoteScreen
import com.example.myapplication.ui.util.Screen
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    val state = mainScreenViewModel.uiState
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditNoteScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(),contentPadding = PaddingValues(16.dp)) {
            items(mainScreenViewModel.uiState.value.notes) { note ->
                NoteItem(
                    noteEntity = note,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(Screen.AddEditNoteScreen.route +
                                "?noteId=${note.nid}") },
                onDeleteClick = {mainScreenViewModel.deleteNote(note)})
            }
        }
    }
}