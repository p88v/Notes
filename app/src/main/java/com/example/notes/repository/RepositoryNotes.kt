package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.dto.Note
import kotlinx.coroutines.flow.Flow

interface RepositoryNotes {


    fun getAll(): Flow<List<Note>>

    suspend fun saveNote(note: Note)
    suspend fun removeById(noteId: Long)
    suspend fun pinById(noteId: Long)


}