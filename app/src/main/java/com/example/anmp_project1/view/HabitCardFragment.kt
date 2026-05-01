package com.example.anmp_project1.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.anmp_project1.databinding.FragmentHabitCardBinding

class HabitCardFragment : Fragment() {
    private lateinit var binding: FragmentHabitCardBinding

    private var currentProgress = 0
    private var maxGoal = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()

        binding.btnIncrement.setOnClickListener {
            if (currentProgress < maxGoal) {
                currentProgress++
                updateUI()
            }
        }

        binding.btnDecrement.setOnClickListener {
            if (currentProgress > 0) {
                currentProgress--
                updateUI()
            }
        }
    }

    private fun updateUI(){
        binding.txtUnitProgress.text = "units"
        binding.txtCurrentProgress.text = currentProgress.toString()
        binding.txtTargetProgress.text = maxGoal.toString()

        binding.progressBar.max = maxGoal
        binding.progressBar.progress = currentProgress

        if (currentProgress >= maxGoal) {
            binding.btnStatus.text = "Completed"
            binding.btnStatus.setTextColor(Color.parseColor("#4CAF50"))
        } else {
            binding.btnStatus.text = "In Progress"
            binding.btnStatus.setTextColor(Color.parseColor("#FF9800"))
        }
    }
}