package com.example.notes.ui

import com.example.notes.dto.Note

data class NotesUiState(
    val notes: List<Note> = emptyList(),
    val empty: Boolean = false,
    val loading: Boolean = false,
    val erroe: String? = null,
)
