package com.example.anmp_project1.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.anmp_project1.databinding.FragmentCreateHabitBinding
import com.example.anmp_project1.model.Habit
import com.example.anmp_project1.viewmodel.HabitDetailViewModel
import com.google.android.material.snackbar.Snackbar

class EditHabitFragment : Fragment(), HabitEditListener {
    private lateinit var binding: FragmentCreateHabitBinding
    private lateinit var viewModel: HabitDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateHabitBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = this
        binding.txtFragmentTitle.text = "Edit Habit"
        binding.btnCreateHabit.text = "Save Changes"

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, Habit.iconOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerInputIcon.adapter = adapter

        viewModel = ViewModelProvider(this).get(HabitDetailViewModel::class.java)
        val id = EditHabitFragmentArgs.fromBundle(requireArguments()).habitId
        viewModel.fetch(id)
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.habitLD.observe(viewLifecycleOwner, Observer { habit ->
            binding.habit = habit
            binding.executePendingBindings()
        })
    }

    override fun onClick(v: View) {
        val habit = binding.habit ?: return

        if (habit.title.isEmpty() || habit.description.isEmpty() ||
            habit.targetString.isEmpty() || habit.unit.isEmpty()
        ) {
            Snackbar.make(v, "All fields must be filled!", Snackbar.LENGTH_SHORT).show()
            if (habit.title.isEmpty()) binding.txtInputTitle.error = "Required"
            if (habit.description.isEmpty()) binding.txtInputDescription.error = "Required"
            if (habit.targetString.isEmpty()) binding.txtInputGoal.error = "Required"
            if (habit.unit.isEmpty()) binding.txtInputUnit.error = "Required"
            return
        }

        viewModel.update(habit)
        Toast.makeText(v.context, "Habit Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}