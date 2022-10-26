package com.example.myapplication.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Database
import com.example.myapplication.data.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(db: Database): ViewModel() {
    private val _uiState= MutableStateFlow(MainScreenState())
    val uiState: StateFlow<MainScreenState> = _uiState.asStateFlow()

    var noteDao = db.noteDao()
    private var getNotesJob: Job? = null

    init {
        getAllNotes()
    }

    fun deleteNote(noteEntity: NoteEntity){
        viewModelScope.launch {
            noteDao.deleteNote(note = noteEntity)
        }
    }


    private fun getAllNotes() {
        getNotesJob?.cancel()
        getNotesJob = noteDao.getAllNotes()
            .onEach { notes ->
                _uiState.value = uiState.value.copy(
                    notes = notes,
                )
            }
            .launchIn(viewModelScope)
    }


}