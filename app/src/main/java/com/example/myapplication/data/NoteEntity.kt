package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey val nid: Int?=null,
    val title: String,
    val note: String,
    val creationDate: String,

    ){
    //Notes will have brackets which will serve as a link marker.
//During parsing those brackets will be removed from text and replaced with link to other
    //note, and added again when saving it to the database.
}
