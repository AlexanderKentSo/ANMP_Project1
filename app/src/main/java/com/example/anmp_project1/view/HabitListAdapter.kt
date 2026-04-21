package com.example.anmp_project1.view

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
        holder.binding.txtTitle.text = habitList[position].title
        holder.binding.btnStatus.text = habitList[position].status
        holder.binding.progressBar.progress = if(habitList[position].target > 0) ((habitList[position].current.toFloat() / habitList[position].target) * 100).toInt() else 0
        holder.binding.txtCurrentProgress.text = habitList[position].current.toString()
        holder.binding.txtTargetProgress.text = habitList[position].target.toString()
        holder.binding.txtUnitProgress.text = habitList[position].unit

        val context = holder.itemView.context
        val resId = context.resources.getIdentifier(
            habitList[position].icon,
            "drawable",
            context.packageName
        )
        holder.binding.imgIcon.setImageResource(resId)
    }

    override fun getItemCount(): Int = habitList.size

    fun updateHabitList(newHabitList:ArrayList<Habit>){
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }

    class HabitViewHolder(var binding: FragmentHabitCardBinding): RecyclerView.ViewHolder(binding.root)
}