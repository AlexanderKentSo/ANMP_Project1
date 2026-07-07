package com.example.anmp_project1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.anmp_project1.model.AppDatabase
import com.example.anmp_project1.model.Habit

class DashboardViewModel(application: Application): AndroidViewModel(application) {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    var userId: Int = 0

    private val habitDao = AppDatabase.getDatabase(application).habitDao()

    fun refresh(){
        val list = habitDao.getHabitsByUser(userId)
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
            habitDao.updateHabit(habit)
            refresh()
        }
    }

    fun decrementProgress(habitId: Int) {
        val currentList = habitsLD.value ?: return
        val habit = currentList.find { it.id == habitId } ?: return
        if (habit.current > 0) {
            habit.current--
            habit.status = "In Progress"
            habitDao.updateHabit(habit)
            refresh()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}