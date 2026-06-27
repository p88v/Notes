package com.example.notes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notes.entity.NoteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY isPinned DESC, id DESC")
    fun getAll(): Flow<List<NoteEntity>>


    @Insert
    suspend fun insert(noteEntity: NoteEntity)

    @Query("UPDATE notes SET title=:title WHERE id = :noteEntityId ")
    suspend fun updById(noteEntityId: Long, title: String)

    suspend fun saveNote(noteEntity: NoteEntity){
        if(noteEntity.id == 0L){
            insert(noteEntity)
        }else updById(noteEntity.id, noteEntity.title)
    }


    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun removeById(noteId: Long)


    @Query("""
        UPDATE notes SET
        isPinned = CASE WHEN isPinned THEN 0 ELSE 1 END
        WHERE id = :noteId
    """)
    suspend fun pinById(noteId: Long)

}