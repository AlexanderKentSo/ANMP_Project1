package com.example.anmp_project1.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anmp_project1.model.Habit

class DashboardViewModel(): ViewModel() {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()

    fun refresh(){
        habitsLD.value = arrayListOf(
            Habit(1, "Drink Water", "Drink 8 glass of water everyday", 3, 8, "Glasses", "baseline_water_drop_24", 1, "In Progress"),
            Habit(2, "Jogging", "Run for 5KM everyday for 5 consecutive days", 2, 5, "Days", "baseline_directions_run_24", 2, "In Progress"),
            Habit(3, "Read Books", "Read 5 books every months", 4, 5, "Books", "baseline_book_24", 1, "In Progress"),
            Habit(4, "Meditate", "Meditate for 15 minutes everyday for 5 consecutive days", 1, 5, "Days", "baseline_emoji_people_24", 2, "In Progress"),
        )
        Log.d("VM DEBUG", "Size: ${habitsLD.value?.size ?: 0}")
    }

    override  fun onCleared() {
        super.onCleared()
    }
}