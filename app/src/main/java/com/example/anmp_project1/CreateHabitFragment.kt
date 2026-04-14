package com.example.anmp_project1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anmp_project1.databinding.FragmentCreateHabitBinding
import com.example.anmp_project1.databinding.FragmentLoginBinding

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