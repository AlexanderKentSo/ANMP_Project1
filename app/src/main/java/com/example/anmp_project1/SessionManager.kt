package com.example.anmp_project1

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("habit_tracker_prefs", Context.MODE_PRIVATE)

    fun saveUserId(userId: Int) {
        prefs.edit().putInt("USER_ID", userId).apply()
    }

    fun getUserId(): Int {
        return prefs.getInt("USER_ID", -1)
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
