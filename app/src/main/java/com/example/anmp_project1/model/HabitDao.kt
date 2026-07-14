package com.example.anmp_project1.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Insert
    fun insertHabit(habit: Habit)

    @Update
    fun updateHabit(habit: Habit)

    @Query("SELECT * FROM habit WHERE userid = :userId")
    fun getHabitsByUser(userId: Int): List<Habit>

    @Query("SELECT * FROM habit WHERE id = :habitId")
    fun getHabitById(habitId: Int): Habit?
}
