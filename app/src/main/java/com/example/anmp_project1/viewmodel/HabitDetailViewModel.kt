package com.example.anmp_project1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anmp_project1.model.Habit
import com.example.anmp_project1.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HabitDetailViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val habitLD = MutableLiveData<Habit>()
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(id: Int) {
        launch {
            val db = buildDb(getApplication())
            habitLD.postValue(db.habitDao().showId(id))
        }
    }

    fun insert(habit: Habit){
        launch{
            val db = buildDb(getApplication())
            db.habitDao().insert(habit)
        }
    }

    fun update(habit: Habit){
        launch {
            val db = buildDb(getApplication())
            db.habitDao().update(habit)
        }
    }
}