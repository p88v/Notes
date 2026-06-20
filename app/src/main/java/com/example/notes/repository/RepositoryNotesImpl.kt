package com.example.notes.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notes.entity.Note
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RepositoryNotesImpl(private val context: Context): RepositoryNotes {


    private var newId: Long = 0

    private var notes = emptyList<Note>()
        get() = field
        set(value){
            field = value
            sync()
        }

    private val data = MutableLiveData(notes)

    init {
        val file = context.filesDir.resolve(FILE_NAME)
        if(file.exists()){
            context.openFileInput(FILE_NAME).bufferedReader().use {
                notes = gson.fromJson(it, type)
                newId = notes.maxOfOrNull { it.id } ?: 0L
                data.value = notes
            }
        }
    }

    private fun sync(){
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(notes))
        }
    }
    override fun getAll(): LiveData<List<Note>> = data

    override fun saveNote(note: Note) {
        if(note.id == 0L){
            notes = listOf(note.copy(
                id = ++newId,
                title = note.title,
                content = note.content
            )) + notes
            data.value = notes
        } else{
            notes = notes.map {
                if(it.id == note.id){
                    it.copy(content = note.content)
                } else {
                    it
                }
            }
            data.value = notes
        }
    }

    override fun removeById(noteId: Long) {
        notes = notes.filter { it.id != noteId }
        data.value = notes
    }

    override fun pinById(noteId: Long) {
    notes = notes.map {
        if(it.id == noteId){
            it.copy(isPinned = !it.isPinned)
        }else{
            it
        }
    }.sortedWith(
        compareByDescending<Note> { it.isPinned }
            .thenByDescending { it.id }
    )
        data.value = notes
    }

    companion object{
        private const val FILE_NAME = "file.json"
        val gson = Gson()
        val type = TypeToken.getParameterized(List::class.java, Note::class.java).type
    }
}
