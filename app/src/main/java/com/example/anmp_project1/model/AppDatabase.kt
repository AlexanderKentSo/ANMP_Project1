package com.example.anmp_project1.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(entities = [User::class, Habit::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "habit_tracker_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback())
                    .allowMainThreadQueries() // Using allowMainThreadQueries for simplicity matching classroom scope
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                Executors.newSingleThreadExecutor().execute {
                    val userDao = database.userDao()
                    val habitDao = database.habitDao()
                    
                    userDao.insertUser(User(name = "user1", password = "123"))
                    userDao.insertUser(User(name = "user2", password = "123"))

                    habitDao.insertHabit(Habit(title = "Drink Water", description = "Drink 8 glass of water everyday", current = 3, target = 8, unit = "Glasses", icon = "baseline_water_drop_24", userid = 1, status = "In Progress"))
                    habitDao.insertHabit(Habit(title = "Jogging", description = "Run for 5KM everyday for 5 consecutive days", current = 2, target = 5, unit = "Days", icon = "baseline_directions_run_24", userid = 2, status = "In Progress"))
                    habitDao.insertHabit(Habit(title = "Read Books", description = "Read 5 books every months", current = 4, target = 5, unit = "Books", icon = "baseline_book_24", userid = 1, status = "In Progress"))
                    habitDao.insertHabit(Habit(title = "Meditate", description = "Meditate for 15 minutes everyday for 5 consecutive days", current = 1, target = 5, unit = "Days", icon = "baseline_emoji_people_24", userid = 2, status = "In Progress"))
                }
            }
        }
    }
}
