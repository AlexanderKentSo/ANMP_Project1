package com.example.anmp_project1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.anmp_project1.databinding.FragmentCreateHabitBinding

class CreateHabitFragment : Fragment() {
    private lateinit var binding: FragmentCreateHabitBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentCreateHabitBinding.inflate(inflater, container, false)
        return binding.root
    }
}