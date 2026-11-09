package com.example.carecircle

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class AddEditTaskActivity : AppCompatActivity() {

    private lateinit var title: TextInputEditText
    private lateinit var dueDate: TextInputEditText
    private lateinit var assignee: TextInputEditText

    private lateinit var saveButton: MaterialButton
    private lateinit var deleteButton: MaterialButton

    private lateinit var database: CareCircleDatabase

    private var taskId: Int = 0
    private var existingTask: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_task)

        title = findViewById(R.id.etTaskTitle)
        dueDate = findViewById(R.id.etDueDate)
        assignee = findViewById(R.id.etAssignee)

        saveButton = findViewById(R.id.btnSaveTask)
        deleteButton = findViewById(R.id.btnDeleteTask)

        database = CareCircleDatabase.getDatabase(this)

        taskId = intent.getIntExtra("TASK_ID", 0)

        findViewById<TextView>(R.id.tvBack).setOnClickListener {
            finish()
        }

        if (taskId != 0) {
            loadTask()
        }

        saveButton.setOnClickListener {
            saveTask()
        }

        deleteButton.setOnClickListener {
            deleteTask()
        }
    }

    private fun loadTask() {

        lifecycleScope.launch {

            val task = database.taskDao().getTaskById(taskId)

            if (task != null) {

                existingTask = task

                title.setText(task.title)
                dueDate.setText(task.dueDate)
                assignee.setText(task.assignee)

                saveButton.text = "Update Task"
                deleteButton.isEnabled = true

            } else {

                Toast.makeText(
                    this@AddEditTaskActivity,
                    "Task not found",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }

    private fun saveTask() {

        val taskTitle = title.text.toString().trim()
        val taskDate = dueDate.text.toString().trim()
        val taskAssignee = assignee.text.toString().trim()

        if (taskTitle.isEmpty()) {
            title.error = "Please enter a task title"
            return
        }

        lifecycleScope.launch {

            if (taskId == 0) {

                val newTask = Task(
                    title = taskTitle,
                    dueDate = taskDate,
                    assignee = taskAssignee
                )

                database.taskDao().insertTask(newTask)

                Toast.makeText(
                    this@AddEditTaskActivity,
                    "Task saved",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val oldTask = existingTask ?: return@launch

                val updatedTask = oldTask.copy(
                    title = taskTitle,
                    dueDate = taskDate,
                    assignee = taskAssignee
                )

                database.taskDao().updateTask(updatedTask)

                Toast.makeText(
                    this@AddEditTaskActivity,
                    "Task updated",
                    Toast.LENGTH_SHORT
                ).show()
            }

            finish()
        }
    }

    private fun deleteTask() {

        val task = existingTask

        if (task == null) {

            Toast.makeText(
                this,
                "No saved task to delete",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        lifecycleScope.launch {

            database.taskDao().deleteTask(task)

            Toast.makeText(
                this@AddEditTaskActivity,
                "Task deleted",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }
    }
}