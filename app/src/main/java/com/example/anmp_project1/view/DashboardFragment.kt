package com.example.anmp_project1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmp_project1.databinding.FragmentDashboardBinding
import com.example.anmp_project1.model.Habit
import com.example.anmp_project1.viewmodel.DashboardViewModel

class DashboardFragment : Fragment(), HabitItemListener {
    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding;
    private val habitListAdapter: HabitListAdapter = HabitListAdapter(arrayListOf(), this)
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
        viewModel.userId = user_id
        viewModel.refresh()

        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = habitListAdapter

        binding.fabCreateHabit.setOnClickListener {
            val action = DashboardFragmentDirections.actionCreateHabitFragment(user_id)
            it.findNavController().navigate(action)
        }

        observeViewModel()
    }

    override fun onDecrement(habit: Habit) {
        if(habit.current > 0) {
            habit.current--
            viewModel.update(habit)
            habitListAdapter.notifyDataSetChanged()
        }
    }

    override fun onIncrement(habit: Habit) {
        if(habit.current < habit.target){
            habit.current++
            viewModel.update(habit)
            habitListAdapter.notifyDataSetChanged()
        }
    }

    override fun onTitleClick(view:View, habit: Habit) {
        val action = DashboardFragmentDirections.actionEditHabitFragment(habit.id)
        view.findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }

    fun observeViewModel() {
        viewModel.habitsLD.observe(viewLifecycleOwner, Observer {
            habitListAdapter.updateHabitList(it)
        })
    }
}