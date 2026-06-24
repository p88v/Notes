package com.example.notes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.dao.NoteDao
import com.example.notes.entity.NoteEntity


@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDb: RoomDatabase() {

    abstract fun  noteDao(): NoteDao

    companion object{

        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb{
            return instance ?: synchronized(this){
                instance ?: buildDataBase(context).also {
                    instance = it
                }
            }
        }

        fun buildDataBase(context: Context): AppDb{
            return Room.databaseBuilder(
                context.applicationContext,
                AppDb::class.java,
                "app.db"
            )
                .allowMainThreadQueries()
                .build()
        }

    }




}