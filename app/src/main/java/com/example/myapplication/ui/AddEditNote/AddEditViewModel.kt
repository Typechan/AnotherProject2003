package com.example.myapplication.ui.AddEditNote

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Database
import com.example.myapplication.data.NoteEntity
import androidx.compose.runtime.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    db: Database,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val noteDao=db.noteDao()
    private var currentNoteId: Int? = null
    private val _noteTitle = mutableStateOf(NoteState(
        hint = "Enter title..."
    ))
    val noteTitle: State<NoteState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteState(
        hint = "Enter some content"
    ))
    val noteContent: State<NoteState> = _noteContent


    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    noteDao.getNoteById(noteId)?.also { note ->
                        currentNoteId = note.nid
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.note,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun enterTitle(title: String){
        _noteTitle.value = noteTitle.value.copy(
            title = title
        )
    }

    fun enterNote(text: String){
        _noteContent.value = noteContent.value.copy(
            text = text
        )
    }

    fun saveNote() {
        viewModelScope.launch {
            noteDao.insertNote(
                NoteEntity(
                    nid = currentNoteId,
                    title = noteTitle.value.title,
                    note = noteContent.value.text,
                    "Today"
                )
            )
        }
    }
}