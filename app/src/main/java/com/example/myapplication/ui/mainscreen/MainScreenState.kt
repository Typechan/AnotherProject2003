package com.example.myapplication.ui.mainscreen

import com.example.myapplication.data.NoteEntity

data class MainScreenState(
    val notes: List<NoteEntity> = emptyList(),
    val scrollPosition:Int?=null,
    val sortType: String?=null
)
