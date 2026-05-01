package com.example.anmp_project1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anmp_project1.model.Habit

class HabitViewModel : ViewModel() {

    val habitsLD = MutableLiveData<ArrayList<Habit>>()

    fun updateHabitList(newHabitList: ArrayList<Habit>) {
        habitsLD.value = newHabitList
    }
}