package com.example.anmp_project1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.anmp_project1.model.AppDatabase
import com.example.anmp_project1.model.Habit

class EditHabitViewModel(application: Application): AndroidViewModel(application) {
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val targetStr = MutableLiveData<String>()
    val unit = MutableLiveData<String>()
    
    var habitId: Int = 0
    private var currentHabit: Habit? = null

    private val habitDao = AppDatabase.getDatabase(application).habitDao()

    fun fetchHabit(id: Int) {
        habitId = id
        currentHabit = habitDao.getHabitById(habitId)
        currentHabit?.let {
            title.value = it.title
            description.value = it.description
            targetStr.value = it.target.toString()
            unit.value = it.unit
        }
    }

    fun updateHabit(selectedIcon: String) {
        currentHabit?.let {
            it.title = title.value ?: ""
            it.description = description.value ?: ""
            it.target = targetStr.value?.toIntOrNull() ?: 0
            it.unit = unit.value ?: ""
            it.icon = selectedIcon
            habitDao.updateHabit(it)
        }
    }
}
