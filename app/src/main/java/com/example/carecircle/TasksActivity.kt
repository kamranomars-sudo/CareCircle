package com.example.carecircle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class TasksActivity : AppCompatActivity() {

    private lateinit var taskTitle: TextView
    private lateinit var taskDetails: TextView
    private lateinit var taskCheckBox: CheckBox

    private val addTaskLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == Activity.RESULT_OK) {

                val data = result.data

                val title = data?.getStringExtra("TASK_TITLE") ?: return@registerForActivityResult
                val date = data.getStringExtra("TASK_DATE").orEmpty()
                val assignee = data.getStringExtra("TASK_ASSIGNEE").orEmpty()

                taskTitle.text = title

                val details = when {
                    date.isNotBlank() && assignee.isNotBlank() ->
                        "$date  •  Assigned to $assignee"

                    date.isNotBlank() ->
                        date

                    assignee.isNotBlank() ->
                        "Assigned to $assignee"

                    else ->
                        "New care task"
                }

                taskDetails.text = details
                taskCheckBox.isChecked = false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        taskTitle = findViewById(R.id.tvTask1Title)
        taskDetails = findViewById(R.id.tvTask1Details)
        taskCheckBox = findViewById(R.id.checkTask1)

        findViewById<TextView>(R.id.tvBack).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(R.id.btnAddTask).setOnClickListener {
            val intent = Intent(this, AddEditTaskActivity::class.java)
            addTaskLauncher.launch(intent)
        }
    }
}