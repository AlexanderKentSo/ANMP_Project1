package com.example.anmp_project1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.anmp_project1.databinding.FragmentCreateHabitBinding
import com.example.anmp_project1.model.Habit
import com.example.anmp_project1.model.IconOption
import com.example.anmp_project1.viewmodel.HabitDetailViewModel
import com.google.android.material.snackbar.Snackbar

class CreateHabitFragment : Fragment(), HabitEditListener {
    private lateinit var binding: FragmentCreateHabitBinding;
    private lateinit var viewModel: HabitDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentCreateHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listener = this
        val userId = CreateHabitFragmentArgs.fromBundle(requireArguments()).userId

        viewModel = ViewModelProvider(this).get(HabitDetailViewModel::class.java)
        binding.habit = Habit("", "", 0, 0, "", "baseline_water_drop_24", userId)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, Habit.iconOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerInputIcon.adapter = adapter

        binding.btnCreateHabit.setOnClickListener {
            val selectedOption = binding.spinnerInputIcon.selectedItem as IconOption
            val selectedIcon = selectedOption.value

            val title = binding.txtInputTitle.text.toString()
            val description = binding.txtInputDescription.text.toString()
            val target = binding.txtInputGoal.text.toString()
            val unit = binding.txtInputUnit.text.toString()

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
                viewModel.habitLD.value?.userid = userId
            }
        }
    }

    override fun onClick(v: View) {
        viewModel.insert(binding.habit!!)
        Toast.makeText(v.context, "Habit Inserted", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}