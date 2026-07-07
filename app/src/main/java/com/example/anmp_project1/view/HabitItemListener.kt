package com.example.anmp_project1.view

import android.view.View
import com.example.anmp_project1.model.Habit

interface HabitItemListener {
    fun onIncrement(habit: Habit)
    fun onDecrement(habit: Habit)
    fun onTitleClick(view: View, habit: Habit)
}

interface HabitEditListener {
    fun onClick(v: View)
}