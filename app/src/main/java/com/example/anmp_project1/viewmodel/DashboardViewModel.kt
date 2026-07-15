package com.example.anmp_project1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.anmp_project1.model.Habit
import com.example.anmp_project1.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DashboardViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val habitsLD = MutableLiveData<List<Habit>>()
    var userId: Int = 0
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun update(habit: Habit) {
        launch {
            val db = buildDb(getApplication())
            db.habitDao().update(habit)
        }
    }

    fun refresh(){
        launch {
            val db = buildDb(getApplication())
            habitsLD.postValue(db.habitDao().showUser(userId))
        }
    }
}