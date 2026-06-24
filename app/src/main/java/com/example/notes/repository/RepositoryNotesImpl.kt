package com.example.notes.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.notes.dao.NoteDao
import com.example.notes.dto.Note
import com.example.notes.entity.NoteEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RepositoryNotesImpl(private val dao: NoteDao): RepositoryNotes {




    private var newId: Long = 0




    override fun getAll(): LiveData<List<Note>>{
        return dao.getAll().map { entities ->
            entities.map { entity ->
                entity.toDto()
            }
        }
    }

    override fun saveNote(note: Note) {
        dao.saveNote(NoteEntity.fromDto(note))
    }

    override fun removeById(noteId: Long) {
        dao.removeById(noteId)
    }

    override fun pinById(noteId: Long) {
        dao.pinById(noteId)
    }


}
