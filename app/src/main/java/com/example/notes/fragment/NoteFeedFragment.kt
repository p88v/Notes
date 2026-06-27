package com.example.notes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.adapter.NotesAdapter
import com.example.notes.adapter.NotesListner
import com.example.notes.databinding.NoteFeedFragmentBinding
import com.example.notes.dto.Note
import com.example.notes.utils.LongArg
import com.example.notes.viewmodel.NotesViewModel
import kotlinx.coroutines.launch

class NoteFeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = NoteFeedFragmentBinding.inflate(inflater, container, false)
        val viewModel by viewModels<NotesViewModel>(ownerProducer = ::requireParentFragment)


        val adapter = NotesAdapter(

            object : NotesListner {


                override fun edit(note: Note) {
                    viewModel.saveNote(note)
                }

                override fun pin(noteId: Long) {
                    viewModel.pinById(noteId)
                }

                override fun remove(noteId: Long) {
                    viewModel.removeNote(noteId)
                }

                override fun open(noteId: Long) {
                    findNavController().navigate(
                        R.id.action_noteFeedFragment_to_detailNnoteFragment,
                        Bundle().apply {
                            longArg = noteId
                        })
                }

            }
        )



        binding.recyclerViewAllNotes.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect { uiState ->
                    adapter.submitList(uiState.notes)
                }
            }
        }


        binding.fabAddNewNote.setOnClickListener {
            findNavController().navigate(
                R.id.action_noteFeedFragment_to_createNoteFragment
            )

        }

        return binding.root
    }

    companion object {
        var Bundle.longArg by LongArg
    }
}