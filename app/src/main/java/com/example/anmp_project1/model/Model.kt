package com.example.anmp_project1.model

data class Habit(
    var id: Int,
    var title: String,
    var description: String,
    var current: Int,
    var target: Int,
    var unit: String,
    var icon: String,
    var userid: Int,
    var status: String
)

data class User(
    var id: Int,
    var name: String,
    var password: String
)