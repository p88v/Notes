package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.dto.Note

interface RepositoryNotes {


    fun getAll(): LiveData<List<Note>>

    fun saveNote(note: Note)
    fun removeById(noteId: Long)
    fun pinById(noteId: Long)


}