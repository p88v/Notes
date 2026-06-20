package com.example.notes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notes.databinding.FragmentCreateNoteBinding
import com.example.notes.viewmodel.NotesViewModel


class CreateNoteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        val viewModel by viewModels<NotesViewModel>(ownerProducer = ::requireParentFragment)




        binding.fabCreateNote.setOnClickListener {

            viewModel.saveNote(
                title = binding.createnoteEditTextTitle.text.toString(),
                content = binding.createNoteTextNote.text.toString()
            )
            findNavController().navigateUp()
        }


        return binding.root
    }





}