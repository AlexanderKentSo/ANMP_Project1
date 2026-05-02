package com.example.anmp_project1.model

import kotlin.collections.plusAssign
import kotlin.text.compareTo
import android.util.Log

data class Habit(
    var id: Int,
    var title: String,
    var description: String,
    var current: Int,
    var target: Int,
    var unit: String,
    var icon: String,
    var userid: Int
) {
    fun increment() {
//        Log.d("Increment","ID: $id, Current: $current, Target: $target")
        if (current < target) {
            current++
        }
    }

    fun decrement() {
//        Log.d("Decrement","ID: $id, Current: $current, Target: $target")
        if (current > 0) {
            current--
        }
    }
}

data class User(
    var id: Int,
    var name: String,
    var password: String
)