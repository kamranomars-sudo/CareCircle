package com.example.carecircle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AddEditTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_task)

        val title = findViewById<TextInputEditText>(R.id.etTaskTitle)
        val dueDate = findViewById<TextInputEditText>(R.id.etDueDate)
        val assignee = findViewById<TextInputEditText>(R.id.etAssignee)

        findViewById<TextView>(R.id.tvBack).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(R.id.btnSaveTask).setOnClickListener {

            val taskTitle = title.text.toString().trim()

            if (taskTitle.isEmpty()) {
                title.error = "Please enter a task title"
                return@setOnClickListener
            }

            val result = Intent().apply {
                putExtra("TASK_TITLE", taskTitle)
                putExtra("TASK_DATE", dueDate.text.toString().trim())
                putExtra("TASK_ASSIGNEE", assignee.text.toString().trim())
            }

            setResult(Activity.RESULT_OK, result)

            Toast.makeText(
                this,
                "Task saved",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }

        findViewById<MaterialButton>(R.id.btnDeleteTask).setOnClickListener {
            Toast.makeText(
                this,
                "No saved task to delete",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}