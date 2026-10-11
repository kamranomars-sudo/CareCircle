package com.example.carecircle

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class AddEditMedicationActivity : AppCompatActivity() {

    private lateinit var etMedicationName: TextInputEditText
    private lateinit var etDosage: TextInputEditText
    private lateinit var etTime: TextInputEditText
    private lateinit var etInstructions: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_medication)

        etMedicationName = findViewById(R.id.etMedicationName)
        etDosage = findViewById(R.id.etDosage)
        etTime = findViewById(R.id.etTime)
        etInstructions = findViewById(R.id.etInstructions)

        findViewById<android.widget.TextView>(R.id.tvBack)
            .setOnClickListener {
                finish()
            }

        etTime.setOnClickListener {
            showTimePicker()
        }

        findViewById<MaterialButton>(R.id.btnSaveMedication)
            .setOnClickListener {
                saveMedication()
            }

        findViewById<MaterialButton>(R.id.btnDeleteMedication)
            .setOnClickListener {
                deleteMedication()
            }
    }

    private fun showTimePicker() {

        val calendar = Calendar.getInstance()

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->

                val amPm =
                    if (selectedHour >= 12) "PM" else "AM"

                val displayHour =
                    when {
                        selectedHour == 0 -> 12
                        selectedHour > 12 -> selectedHour - 12
                        else -> selectedHour
                    }

                etTime.setText(
                    String.format(
                        "%d:%02d %s",
                        displayHour,
                        selectedMinute,
                        amPm
                    )
                )
            },
            hour,
            minute,
            false
        ).show()
    }

    private fun saveMedication() {

        val name = etMedicationName.text.toString().trim()
        val dosage = etDosage.text.toString().trim()
        val time = etTime.text.toString().trim()
        val instructions = etInstructions.text.toString().trim()

        if (name.isEmpty()) {
            etMedicationName.error = "Enter medication name"
            return
        }

        if (dosage.isEmpty()) {
            etDosage.error = "Enter dosage"
            return
        }

        if (time.isEmpty()) {
            Toast.makeText(
                this,
                "Please select a time",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val preferences =
            getSharedPreferences("CareCirclePrefs", MODE_PRIVATE)

        preferences.edit()
            .putString("medication_name", name)
            .putString("medication_dosage", dosage)
            .putString("medication_time", time)
            .putString("medication_instructions", instructions)
            .apply()

        Toast.makeText(
            this,
            "Medication saved",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }

    private fun deleteMedication() {

        val preferences =
            getSharedPreferences("CareCirclePrefs", MODE_PRIVATE)

        preferences.edit()
            .remove("medication_name")
            .remove("medication_dosage")
            .remove("medication_time")
            .remove("medication_instructions")
            .apply()

        Toast.makeText(
            this,
            "Medication deleted",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }
}