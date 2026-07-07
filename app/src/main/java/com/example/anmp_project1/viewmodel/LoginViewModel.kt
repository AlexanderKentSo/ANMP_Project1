package com.example.anmp_project1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.anmp_project1.model.AppDatabase
import com.example.anmp_project1.model.User

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()

    fun login(username: String, password: String): User? {
        return userDao.login(username, password)
    }
}