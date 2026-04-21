package com.example.anmp_project1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_project1.databinding.FragmentDashboardBinding
import com.example.anmp_project1.viewmodel.DashboardViewModel

class DashboardFragment : Fragment() {
    private lateinit var viewModel: DashboardViewModel
    private val habitListAdapter = HabitListAdapter(arrayListOf())
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

        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        viewModel.refresh()

        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = habitListAdapter

        binding.fabCreateHabit.setOnClickListener {
            val action = DashboardFragmentDirections.actionCreateHabitFragment(user_id)
            it.findNavController().navigate(action)
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.habitsLD.observe(viewLifecycleOwner, Observer {
            habitListAdapter.updateHabitList(it)
        })
    }
}