package com.example.carecircle

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.util.Calendar

class AddEditAppointmentActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etDate: EditText
    private lateinit var etTime: EditText
    private lateinit var etAddress: EditText
    private lateinit var etNotes: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_appointment)

        etTitle = findViewById(R.id.etTitle)
        etDate = findViewById(R.id.etDate)
        etTime = findViewById(R.id.etTime)
        etAddress = findViewById(R.id.etAddress)
        etNotes = findViewById(R.id.etNotes)

        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        etDate.setOnClickListener {
            showDatePicker()
        }

        etTime.setOnClickListener {
            showTimePicker()
        }

        findViewById<MaterialButton>(R.id.btnSaveAppointment).setOnClickListener {
            saveAppointment()
        }

        findViewById<MaterialButton>(R.id.btnDeleteAppointment).setOnClickListener {
            Toast.makeText(
                this,
                "Appointment deleted",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }

        findViewById<MaterialButton>(R.id.btnMap).setOnClickListener {

            val address = etAddress.text.toString().trim()

            val intent = Intent(this, AppointmentMapActivity::class.java)

            if (address.isNotEmpty()) {
                intent.putExtra("appointment_address", address)
            }

            startActivity(intent)
        }
    }

    private fun showDatePicker() {

        val calendar = Calendar.getInstance()

        val dialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                etDate.setText(
                    "${month + 1}/$day/$year"
                )
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        dialog.show()
    }

    private fun showTimePicker() {

        val calendar = Calendar.getInstance()

        val dialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->

                val amPm = if (hourOfDay >= 12) "PM" else "AM"

                val hour = when {
                    hourOfDay == 0 -> 12
                    hourOfDay > 12 -> hourOfDay - 12
                    else -> hourOfDay
                }

                etTime.setText(
                    String.format("%d:%02d %s", hour, minute, amPm)
                )
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )

        dialog.show()
    }

    private fun saveAppointment() {

        val title = etTitle.text.toString().trim()
        val date = etDate.text.toString().trim()
        val time = etTime.text.toString().trim()
        val address = etAddress.text.toString().trim()

        if (title.isEmpty()) {
            etTitle.error = "Enter appointment title"
            return
        }

        if (date.isEmpty()) {
            etDate.error = "Select a date"
            return
        }

        if (time.isEmpty()) {
            etTime.error = "Select a time"
            return
        }

        if (address.isEmpty()) {
            etAddress.error = "Enter an address"
            return
        }

        Toast.makeText(
            this,
            "Appointment saved",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }
}