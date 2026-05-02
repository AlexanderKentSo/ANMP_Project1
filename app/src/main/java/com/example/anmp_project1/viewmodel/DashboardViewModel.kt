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

    override  fun onCleared() {
        super.onCleared()
    }
}