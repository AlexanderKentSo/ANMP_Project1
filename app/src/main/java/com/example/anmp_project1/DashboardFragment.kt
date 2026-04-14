package com.example.anmp_project1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.anmp_project1.databinding.FragmentDashboardBinding
import com.example.anmp_project1.databinding.FragmentLoginBinding

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding;
    private var user_id: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        user_id = DashboardFragmentArgs.fromBundle(requireArguments()).userId

        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabCreateHabit.setOnClickListener {
            val action = DashboardFragmentDirections.actionCreateHabitFragment(user_id)
            it.findNavController().navigate(action)
        }
    }
}