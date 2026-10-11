package com.example.carecircle

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class AppointmentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)

        // Back button
        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Add Appointment button
        findViewById<MaterialButton>(R.id.btnAddAppointment).setOnClickListener {
            val intent = Intent(this, AddEditAppointmentActivity::class.java)
            startActivity(intent)
        }
    }
}