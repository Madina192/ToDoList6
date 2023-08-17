package com.example.todolist6

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val tasks = mutableListOf<Task>()

    fun saveTask(title: String) {
        tasks.add(Task(title))
    }

    fun removeTask(task: Task) {
        tasks.remove(task)
    }

    fun getAllTasks(): List<Task> {
        return tasks
    }

    fun markTaskAsDone(task: Task) {
        task.isDone = true
    }
}