package com.example.anmp_project1.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE name=:name AND password=:password")
    fun login(name:String, password: String): User?
}