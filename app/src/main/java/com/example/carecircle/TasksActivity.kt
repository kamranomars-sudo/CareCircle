package com.example.carecircle

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.launch

class TasksActivity : AppCompatActivity() {

    private lateinit var taskTitle: TextView
    private lateinit var taskDetails: TextView
    private lateinit var taskCheckBox: CheckBox
    private lateinit var taskCard: MaterialCardView
    private lateinit var database: CareCircleDatabase

    private var currentTask: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        taskTitle = findViewById(R.id.tvTask1Title)
        taskDetails = findViewById(R.id.tvTask1Details)
        taskCheckBox = findViewById(R.id.checkTask1)
        taskCard = findViewById(R.id.cardTask1)

        database = CareCircleDatabase.getDatabase(this)

        findViewById<TextView>(R.id.tvBack).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(R.id.btnAddTask).setOnClickListener {
            val intent = Intent(this, AddEditTaskActivity::class.java)
            startActivity(intent)
        }

        taskCard.setOnClickListener {
            val task = currentTask ?: return@setOnClickListener

            val intent = Intent(this, AddEditTaskActivity::class.java)
            intent.putExtra("TASK_ID", task.id)
            startActivity(intent)
        }

        taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
            updateCompletion(isChecked)
        }
    }

    override fun onResume() {
        super.onResume()
        loadLatestTask()
    }

    private fun loadLatestTask() {
        lifecycleScope.launch {

            val tasks = database.taskDao().getAllTasks()

            if (tasks.isNotEmpty()) {

                val task = tasks.first()
                currentTask = task

                taskCard.visibility = View.VISIBLE
                taskTitle.text = task.title

                taskDetails.text = when {
                    task.dueDate.isNotBlank() &&
                            task.assignee.isNotBlank() ->
                        "${task.dueDate}  •  Assigned to ${task.assignee}"

                    task.dueDate.isNotBlank() ->
                        task.dueDate

                    task.assignee.isNotBlank() ->
                        "Assigned to ${task.assignee}"

                    else ->
                        "New care task"
                }

                taskCheckBox.setOnCheckedChangeListener(null)
                taskCheckBox.isChecked = task.completed

                taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
                    updateCompletion(isChecked)
                }

            } else {

                currentTask = null
                taskCard.visibility = View.GONE
            }
        }
    }

    private fun updateCompletion(isChecked: Boolean) {
        val task = currentTask ?: return

        if (task.completed == isChecked) {
            return
        }

        lifecycleScope.launch {
            database.taskDao()
                .updateTaskCompletion(task.id, isChecked)

            currentTask = task.copy(completed = isChecked)
        }
    }
}