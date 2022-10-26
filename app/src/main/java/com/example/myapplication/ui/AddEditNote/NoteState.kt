package com.example.myapplication.ui.AddEditNote

import com.example.myapplication.data.NoteEntity

data class NoteState(
    val title: String="",
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
