package com.example.anmp_project1.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE name = :username AND password = :password")
    fun login(username: String, password: String): User?
}
