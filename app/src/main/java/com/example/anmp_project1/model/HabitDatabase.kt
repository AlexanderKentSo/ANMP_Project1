package com.example.anmp_project1.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Habit::class, User::class], version = 1)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: HabitDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "habit_db"

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            HabitDatabase::class.java,
            DB_NAME
        ).addCallback(dbSeederCallback).build()

        operator fun invoke(context: Context): HabitDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private val dbSeederCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    db.execSQL("INSERT INTO user (id, name, password) VALUES (1, 'user1', '123')")
                    db.execSQL("INSERT INTO user (id, name, password) VALUES (2, 'user2', '123')")

                    db.execSQL("""
                        INSERT INTO habit (id, title, description, current, target, unit, icon, user_id) 
                        VALUES (1, 'Drink Water', 'Drink 8 glass of water everyday', 0, 8, 'Glasses', 'baseline_water_drop_24', 1)
                    """.trimIndent())
                    db.execSQL("""
                        INSERT INTO habit (id, title, description, current, target, unit, icon, user_id) 
                        VALUES (2, 'Jogging', 'Run for 5KM everyday for 5 consecutive days', 0, 5, 'Days', 'baseline_directions_run_24', 2)
                    """.trimIndent())
                    db.execSQL("""
                        INSERT INTO habit (id, title, description, current, target, unit, icon, user_id) 
                        VALUES (3, 'Read Books', 'Read 5 books every months', 0, 5, 'Books', 'baseline_book_24', 1)
                    """.trimIndent())
                    db.execSQL("""
                        INSERT INTO habit (id, title, description, current, target, unit, icon, user_id) 
                        VALUES (4, 'Meditate', 'Meditate for 15 minutes everyday for 5 consecutive days', 0, 5, 'Days', 'baseline_emoji_people_24', 2)
                    """.trimIndent())
                }
            }
        }
    }
}