package com.example.anmp_project1.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project1.databinding.FragmentHabitCardBinding
import com.example.anmp_project1.model.Habit

class HabitListAdapter(val habitList:ArrayList<Habit>, private val listener: HabitItemListener)
    : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>(), HabitItemListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = FragmentHabitCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }
    override fun onBindViewHolder(holder: HabitViewHolder, position: Int){
        holder.binding.habit = habitList[position]
        holder.binding.listener = listener
        holder.binding.executePendingBindings()
    }
    override fun getItemCount(): Int = habitList.size
    fun updateHabitList(newHabitList:List<Habit>){
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }
    class HabitViewHolder(var binding: FragmentHabitCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onDecrement(habit: Habit) {
        listener.onDecrement(habit)
    }

    override fun onIncrement(habit: Habit) {
        listener.onIncrement(habit)
    }

    override fun onTitleClick(view: View, habit: Habit) {
        listener.onTitleClick(view, habit)
    }
}