package com.example.anmp_project1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.anmp_project1.databinding.FragmentEditHabitBinding
import com.example.anmp_project1.viewmodel.EditHabitViewModel
import com.google.android.material.snackbar.Snackbar

class EditHabitFragment : Fragment() {
    private lateinit var binding: FragmentEditHabitBinding
    private lateinit var viewModel: EditHabitViewModel

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
        binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this).get(EditHabitViewModel::class.java)
        
        // Pass viewmodel to DataBinding
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        
        val habitId = EditHabitFragmentArgs.fromBundle(requireArguments()).habitId

        viewModel.fetchHabit(habitId)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, iconLabels)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerInputIcon.adapter = adapter

        binding.btnSubmit.setOnClickListener {
            if (viewModel.title.value.isNullOrEmpty() || viewModel.targetStr.value.isNullOrEmpty()) {
                Snackbar.make(view, "Title and Goal must be filled!", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val selectedIconIndex = binding.spinnerInputIcon.selectedItemPosition
            val selectedIcon = iconOptions[selectedIconIndex]
            
            viewModel.updateHabit(selectedIcon)
            Snackbar.make(view, "Habit updated successfully!", Snackbar.LENGTH_SHORT).show()
            it.findNavController().popBackStack()
        }
    }
}
