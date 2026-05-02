package com.example.anmp_project1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anmp_project1.DummyDataSource
import com.example.anmp_project1.model.Habit

class DashboardViewModel(): ViewModel() {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    var userId: Int = 0

    fun refresh(){
        val list = DummyDataSource.getHabitsByUser(userId)
        habitsLD.value = ArrayList(list)
    }

    fun incrementProgress(habitId: Int) {
        val currentList = habitsLD.value ?: return
        val habit = currentList.find { it.id == habitId } ?: return
        if (habit.current < habit.target) {
            habit.current++
            if (habit.current >= habit.target) {
                habit.status = "Completed"
            }
            DummyDataSource.updateHabit(habit)
            habitsLD.value = currentList
        }
    }

    fun decrementProgress(habitId: Int) {
        val currentList = habitsLD.value ?: return
        val habit = currentList.find { it.id == habitId } ?: return
        if (habit.current > 0) {
            habit.current--
            habit.status = "In Progress"
            DummyDataSource.updateHabit(habit)
            habitsLD.value = currentList
        }
    }

    override  fun onCleared() {
        super.onCleared()
    }
}