package com.example.carecircle

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val dueDate: String,

    val assignee: String,

    val completed: Boolean = false
)