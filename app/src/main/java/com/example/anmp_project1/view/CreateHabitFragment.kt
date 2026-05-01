package com.example.anmp_project1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.anmp_project1.DummyDataSource
import com.example.anmp_project1.databinding.FragmentCreateHabitBinding
import com.example.anmp_project1.model.Habit
import com.google.android.material.snackbar.Snackbar

class CreateHabitFragment : Fragment() {
    private lateinit var binding: FragmentCreateHabitBinding;

    private val iconOptions = listOf(
        "baseline_water_drop_24",
        "baseline_emoji_people_24",
        "baseline_directions_run_24",
        "baseline_book_24"
    )

    private val iconLabels = listOf(
        "Water Drop",
        "Fitness",
        "Running",
        "Book"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentCreateHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = CreateHabitFragmentArgs.fromBundle(requireArguments()).userId

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, iconLabels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerInputIcon.adapter = adapter

        binding.btnCreateHabit.setOnClickListener {
            val title = binding.txtInputTitle.text.toString()
            val description = binding.txtInputDescription.text.toString()
            val goal = binding.txtInputGoal.text.toString()
            val unit = binding.txtInputUnit.text.toString()

            if (title.isEmpty() || description.isEmpty() || goal.isEmpty() || unit.isEmpty()) {
                Snackbar.make(view, "All fields must be filled!", Snackbar.LENGTH_SHORT).show()
                if (title.isEmpty()) binding.txtInputTitle.error = "Required"
                if (description.isEmpty()) binding.txtInputDescription.error = "Required"
                if (goal.isEmpty()) binding.txtInputGoal.error = "Required"
                if (unit.isEmpty()) binding.txtInputUnit.error = "Required"
            } else {
                val selectedIconIndex = binding.spinnerInputIcon.selectedItemPosition
                val selectedIcon = iconOptions[selectedIconIndex]

                val newHabit = Habit(
                    id = DummyDataSource.getNextId(),
                    title = title,
                    description = description,
                    current = 0,
                    target = goal.toInt(),
                    unit = unit,
                    icon = selectedIcon,
                    userid = userId,
                    status = "In Progress"
                )

                DummyDataSource.addHabit(newHabit)
                Snackbar.make(view, "Habit created successfully!", Snackbar.LENGTH_SHORT).show()
                it.findNavController().popBackStack()
            }
        }

    }
}