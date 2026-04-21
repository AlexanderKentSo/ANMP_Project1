package com.example.anmp_project1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.anmp_project1.DummyDataSource
import com.example.anmp_project1.model.User

class LoginViewModel(): ViewModel() {
    fun login(username: String, password: String): User? {
        return DummyDataSource.login(username, password)
    }
}