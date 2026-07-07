package com.example.anmp_project1.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project1.databinding.FragmentHabitCardBinding
import com.example.anmp_project1.model.Habit

interface HabitCardListener {
    fun onIncrement(habitId: Int)
    fun onDecrement(habitId: Int)
    fun onTitleClicked(habitId: Int)
}

class HabitListAdapter(
    val habitList: ArrayList<Habit>,
    val listener: HabitCardListener
): RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = FragmentHabitCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int){
        val habit = habitList[position]
        holder.binding.habit = habit
        holder.binding.listener = listener

        val context = holder.itemView.context
        val resId = context.resources.getIdentifier(
            habit.icon,
            "drawable",
            context.packageName
        )
        if (resId != 0) {
            holder.binding.imgIcon.setImageResource(resId)
        }
    }

    override fun getItemCount(): Int = habitList.size

    fun updateHabitList(newHabitList: ArrayList<Habit>){
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }

    class HabitViewHolder(var binding: FragmentHabitCardBinding): RecyclerView.ViewHolder(binding.root)
}