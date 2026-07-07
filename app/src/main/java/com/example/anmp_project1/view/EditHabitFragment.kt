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
import com.example.anmp_project1.model.IconOption
import com.example.anmp_project1.viewmodel.HabitDetailViewModel
import com.google.android.material.snackbar.Snackbar

class EditHabitFragment : Fragment(), HabitEditListener {
    private lateinit var binding: FragmentCreateHabitBinding
    private lateinit var viewModel: HabitDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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

        // inisiasi viewModel
        viewModel = ViewModelProvider(this).get(HabitDetailViewModel::class.java)
        val id = EditHabitFragmentArgs.fromBundle(requireArguments()).habitId
        viewModel.fetch(id)
        observeViewModel()

        val selectedOption = binding.spinnerInputIcon.selectedItem as IconOption
        val selectedIcon = selectedOption.value

        val title = binding.txtInputTitle.text.toString()
        val description = binding.txtInputDescription.text.toString()
        val target = binding.txtInputGoal.text.toString()
        val unit = binding.txtInputUnit.text.toString()

        binding.btnCreateHabit.setOnClickListener {
            if (title.isEmpty() || description.isEmpty() || target.isEmpty() || unit.isEmpty()) {
                Snackbar.make(view, "All fields must be filled!", Snackbar.LENGTH_SHORT).show()
                if (title.isEmpty()) binding.txtInputTitle.error = "Required"
                if (description.isEmpty()) binding.txtInputDescription.error = "Required"
                if (target.isEmpty()) binding.txtInputGoal.error = "Required"
                if (unit.isEmpty()) binding.txtInputUnit.error = "Required"
            } else {
                viewModel.habitLD.value?.title = title
                viewModel.habitLD.value?.description = description
                viewModel.habitLD.value?.icon = selectedIcon
                viewModel.habitLD.value?.target = target.toInt()
                viewModel.habitLD.value?.unit = unit
            }
        }
    }

    fun observeViewModel() {
        viewModel.habitLD.observe(viewLifecycleOwner, Observer {
            binding.habit = it
        })
    }

    override fun onClick(v: View) {
        viewModel.update(binding.habit!!)
        Toast.makeText(v.context, "Habit Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}