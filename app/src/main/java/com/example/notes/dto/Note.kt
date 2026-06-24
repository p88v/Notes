package com.example.notes.dto

data class Note(
    val id: Long,
    val title: String,
    val content: String,
    val isPinned: Boolean = false,
)