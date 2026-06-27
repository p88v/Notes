package com.example.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.dao.NoteDao
import com.example.notes.db.AppDb
import com.example.notes.dto.Note
import com.example.notes.repository.RepositoryNotes
import com.example.notes.repository.RepositoryNotesImpl
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {

    private val dao = AppDb.getInstance(application).noteDao()


    private val repository: RepositoryNotes = RepositoryNotesImpl(dao)


    val data: StateFlow<List<Note>> = repository.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    fun saveNote(id: Long = 0L, title: String, content: String, isPinned: Boolean = false){
        viewModelScope.launch {
            repository.saveNote(Note(id, title, content, isPinned))
        }
    }
    fun saveNote(note: Note){
        viewModelScope.launch {
            repository.saveNote(note)
        }
    }

    fun removeNote(noteId: Long){
        viewModelScope.launch {
            repository.removeById(noteId)
        }
    }


    fun pinById(noteId: Long){
        viewModelScope.launch {
            repository.pinById(noteId)
        }
    }



}