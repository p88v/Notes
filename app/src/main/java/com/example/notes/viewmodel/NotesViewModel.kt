package com.example.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notes.entity.Note
import com.example.notes.repository.RepositoryNotes
import com.example.notes.repository.RepositoryNotesImpl

class NotesViewModel(application: Application): AndroidViewModel(application) {



    private val repository: RepositoryNotes = RepositoryNotesImpl(application)


    val data = repository.getAll()

    fun saveNote(id: Long = 0L, title: String, content: String, isPinned: Boolean = false){
        repository.saveNote(Note(id, title, content, isPinned))
    }
    fun saveNote(note: Note){
        repository.saveNote(note)
    }

    fun removeNote(noteId: Long){
        repository.removeById(noteId)
    }


    fun pinById(noteId: Long){
        repository.pinById(noteId)
    }



}