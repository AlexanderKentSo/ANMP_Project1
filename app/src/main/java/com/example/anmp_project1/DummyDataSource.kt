package com.example.anmp_project1

import com.example.anmp_project1.model.Habit
import com.example.anmp_project1.model.User

object DummyDataSource {
    private val users = mutableListOf(
        User(1, "user1", "123"),
        User(2, "user2", "123")
    )

    private val habits = mutableListOf(
        Habit(1, "Drink Water", "Drink 8 glass of water everyday", 3, 8, "Glasses", "baseline_water_drop_24", 1, "In Progress"),
        Habit(2, "Jogging", "Run for 5KM everyday for 5 consecutive days", 2, 5, "Days", "baseline_directions_run_24", 2, "In Progress"),
        Habit(3, "Read Books", "Read 5 books every months", 4, 5, "Books", "baseline_book_24", 1, "In Progress"),
        Habit(4, "Meditate", "Meditate for 15 minutes everyday for 5 consecutive days", 1, 5, "Days", "baseline_emoji_people_24", 2, "In Progress")
    )

    fun login(username: String, password: String): User? {
        return users.find { it.name == username && it.password == password }
    }

    fun getHabitsByUser(userId: Int): List<Habit> {
        return habits.filter { it.userid == userId }
    }
}