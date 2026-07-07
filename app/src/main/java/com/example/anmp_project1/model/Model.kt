package com.example.anmp_project1.model

import kotlin.collections.plusAssign
import kotlin.text.compareTo
import android.util.Log
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @ColumnInfo(name="title")
    var title: String,
    @ColumnInfo(name="description")
    var description: String,
    @ColumnInfo(name="current")
    var current: Int,
    @ColumnInfo(name="target")
    var target: Int,
    @ColumnInfo(name="unit")
    var unit: String,
    @ColumnInfo(name="icon")
    var icon: String,
    @ColumnInfo(name="user_id")
    var userid: Int
) {
    @PrimaryKey(autoGenerate=true)
    var id:Int=0

    var targetString: String
        get() = if (target == 0) "" else target.toString()
        set(value) { target = value.toIntOrNull() ?: 0 }

    var iconPosition: Int
        get() {
            val index = Companion.iconOptions.indexOfFirst { it.value == icon }
            return if (index == -1) 0 else index // Jika tidak ketemu, default ke indeks 0
        }
        set(value) {
            if (value in Habit.iconOptions.indices) {
                icon = Habit.iconOptions[value].value
            }
        }

    companion object {
        val iconOptions = listOf(
            IconOption("baseline_water_drop_24", "Water Drop"),
            IconOption("baseline_emoji_people_24", "Fitness"),
            IconOption("baseline_directions_run_24", "Running"),
            IconOption("baseline_book_24", "Book")
        )
    }
}

data class IconOption(val value: String, val label: String) {
    override fun toString(): String = label
}

@Entity
data class User(
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name="password")
    var password: String
) {
    @PrimaryKey(autoGenerate=true)
    var id: Int=0
}