package com.example.anmp_project1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.anmp_project1.databinding.FragmentCreateHabitBinding
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController

class CreateHabitFragment : Fragment() {
    private lateinit var binding: FragmentCreateHabitBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentCreateHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = CreateHabitFragmentArgs.fromBundle(requireArguments()).userId

        binding.btnCreateHabit.setOnClickListener {
            val title = binding.txtInputTitle.text.toString()
            val description = binding.txtInputDescription.text.toString()
            val goal = binding.txtInputGoal.text.toString()
            val unit = binding.txtInputUnit.text.toString()


            if (title.isEmpty() || description.isEmpty() || goal.isEmpty() || unit.isEmpty()) {
                Snackbar.make(view, "All fields must be filled!", Snackbar.LENGTH_SHORT).show()
                if (title.isEmpty()) binding.txtInputTitle.error = "Required"
            } else {
                it.findNavController().popBackStack()
            }
        }

    }
}