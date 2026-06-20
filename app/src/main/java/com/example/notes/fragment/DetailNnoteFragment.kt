package com.example.notes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notes.adapter.NotesAdapter
import com.example.notes.adapter.NotesListner
import com.example.notes.databinding.FragmentDetailNnoteBinding
import com.example.notes.entity.Note
import com.example.notes.fragment.NoteFeedFragment.Companion.longArg
import com.example.notes.viewmodel.NotesViewModel
import kotlin.getValue


class DetailNnoteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailNnoteBinding.inflate(inflater, container, false)
        val viewModel by viewModels<NotesViewModel>(ownerProducer = ::requireParentFragment)

        val postID = arguments?.longArg
        val post_detail = viewModel.data.value?.filter { it.id == postID }



        val adapter = NotesAdapter(
            object : NotesListner {
                override fun pin(noteId: Long) {
                    viewModel.pinById(noteId)
                }

                override fun remove(noteId: Long) {
                    viewModel.removeNote(noteId)
                    findNavController().navigateUp()
                }

                override fun edit(note: Note) {
                    viewModel.saveNote(note)
                }

                override fun open(noteId: Long) {
                    Toast.makeText(context, "Вы уже на детальном просмотре своей заметки", Toast.LENGTH_SHORT).show()
                }
            }
        )

        binding.listOfOneNote.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner){
            adapter.submitList(post_detail)
        }








        return binding.root
    }
}

