package com.example.anmp_project1.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(habit: Habit)

    @Update
    fun update(habit: Habit)

    @Query("SELECT * FROM habit WHERE id=:id")
    fun showId(id: Int): Habit

    @Query("SELECT * FROM habit WHERE user_id=:userId")
    fun showUser(userId: Int): List<Habit>
}