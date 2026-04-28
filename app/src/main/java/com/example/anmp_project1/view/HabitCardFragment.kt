package com.example.anmp_project1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.anmp_project1.R
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_habit_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi tampilan awal
        updateUI()

        // Logika Tombol Tambah (+)
        binding.btnIncrement.setOnClickListener {
            if (currentProgress < maxGoal) {
                currentProgress++
                updateUI()
            }
        }

        // Logika Tombol Kurang (-)
        binding.
            btnDecrement.setOnClickListener {
            if (currentProgress > 0) {
                currentProgress--
                updateUI()
            }
        }
    }

    private fun updateUI(){
        // 1. Update Teks Progress (misal: "3 / 10 glasses")
        binding.txtUnitProgress.text = "$currentProgress / $maxGoal units"

        // 2. Update Progress Bar
        binding.progressBar.max = maxGoal
        binding.progressBar.progress = currentProgress

        // 3. Logika Status (In Progress atau Completed)
        if (currentProgress >= maxGoal) {
            binding.btnStatus.text = "Completed"
            // Opsional: ganti warna status jika diajarkan (contoh ke hijau)
            // binding.txtStatus.setTextColor(Color.GREEN)
        } else {
            binding.btnStatus.text = "In Progress"
        }
    }
}