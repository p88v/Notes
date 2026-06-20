package com.example.notes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notes.databinding.FragmentEditNoteBinding
import com.example.notes.viewmodel.NotesViewModel
import kotlin.getValue


class EditNoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        val viewModel by viewModels<NotesViewModel>(ownerProducer = ::requireParentFragment)

        return binding.root
    }
}