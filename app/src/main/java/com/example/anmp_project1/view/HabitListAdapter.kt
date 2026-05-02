package com.example.anmp_project1.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_project1.databinding.FragmentHabitCardBinding
import com.example.anmp_project1.model.Habit

class HabitListAdapter(val habitList:ArrayList<Habit>): RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = FragmentHabitCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int){
        val habit = habitList[position]

        holder.binding.txtTitle.text = habit.title
        holder.binding.txtDescription.text = habit.description
        holder.binding.txtCurrentProgress.text = habit.current.toString()
        holder.binding.txtTargetProgress.text = habit.target.toString()
        holder.binding.txtUnitProgress.text = habit.unit

        holder.binding.progressBar.max = habit.target
        holder.binding.progressBar.progress = habit.current

        if (habit.current >= habit.target) {
            holder.binding.btnStatus.text = "Completed"
            holder.binding.btnStatus.setTextColor(Color.parseColor("#4CAF50"))
        } else {
            holder.binding.btnStatus.text = "In Progress"
            holder.binding.btnStatus.setTextColor(Color.parseColor("#FF9800"))
        }

        val context = holder.itemView.context
        val resId = context.resources.getIdentifier(
            habit.icon,
            "drawable",
            context.packageName
        )
        if (resId != 0) {
            holder.binding.imgIcon.setImageResource(resId)
        }

        holder.binding.btnIncrement.setOnClickListener {
            habit.increment()
            notifyItemChanged(position)
        }

        holder.binding.btnDecrement.setOnClickListener {
            habit.decrement()
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = habitList.size

    fun updateHabitList(newHabitList:ArrayList<Habit>){
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }

    class HabitViewHolder(var binding: FragmentHabitCardBinding): RecyclerView.ViewHolder(binding.root)
}