package com.example.notes.entity

data class Note(
    val id: Long,
    val title: String,
    val content: String,
    val isPinned: Boolean = false,
)
