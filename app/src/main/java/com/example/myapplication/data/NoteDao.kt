package com.example.myapplication.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE nid = :id")
    suspend fun getNoteById(id: Int): NoteEntity?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}