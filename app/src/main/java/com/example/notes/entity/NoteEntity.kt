package com.example.notes.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notes.dto.Note


@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val content: String,
    val isPinned: Boolean = false,
){

    fun toDto(): Note{
        return Note(
            id = id,
            title = title,
            content = content,
            isPinned = isPinned
        )
    }


    companion object{
        fun fromDto(note: Note): NoteEntity{
            return NoteEntity(
                id = note.id,
                title = note.title,
                content = note.content,
                isPinned = note.isPinned
            )
        }
    }

}
