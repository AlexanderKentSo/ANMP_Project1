package com.example.anmp_project1.util

import android.content.Context
import com.example.anmp_project1.model.HabitDatabase

val DB_NAME = "habitdb"

fun buildDb(context : Context): HabitDatabase {
    val db = HabitDatabase.buildDatabase(context)
    return db
}