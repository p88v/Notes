package com.example.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.NoteCardBinding
import com.example.notes.dto.Note


interface NotesListner {
    fun pin(noteId: Long)
    fun remove(noteId: Long)
    fun edit(note: Note)
    fun open(noteId: Long)
}

object NotesDiffCallBack : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(
        oldItem: Note,
        newItem: Note
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Note,
        newItem: Note
    ): Boolean = oldItem == newItem
}

class NotesAdapter(
    private val listner: NotesListner
) : ListAdapter<Note, NoteViewHolder>(NotesDiffCallBack) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        val binding = NoteCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding, listner)
    }

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int
    ) {
        val note = getItem(position)
        holder.bind(note)
    }
}

class NoteViewHolder(
    private val binding: NoteCardBinding,
    private val listner: NotesListner,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(note: Note) {

        with(binding) {

            tvTextNoteTitle.text = note.title

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.pop_pup)
                    setOnMenuItemClickListener { item ->

                        when (item.itemId) {
                            R.id.MenuPinPost -> {
                                listner.pin(note.id)
                                true
                            }

                            R.id.MenuEditPost -> {
                                listner.edit(note)
                                true
                            }

                            R.id.MenuRemovePost -> {
                                listner.remove(note.id)
                                true
                            }

                            else -> false
                        }

                    }
                }.show()
            }

            tvTextNoteTitle.setOnClickListener {
                listner.open(note.id)
            }


        }
    }
}