package com.example.carecircle

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class CareProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_care_profile)

        // Back
        findViewById<TextView>(R.id.tvBack).setOnClickListener {
            finish()
        }

        // Care Plan
        findViewById<MaterialButton>(R.id.btnCarePlan).setOnClickListener {
            val intent = Intent(this, CarePlanActivity::class.java)
            startActivity(intent)
        }

        // Medications
        findViewById<MaterialButton>(R.id.btnMedications).setOnClickListener {
            val intent = Intent(this, MedicationsActivity::class.java)
            startActivity(intent)
        }

        // Appointments
        findViewById<MaterialButton>(R.id.btnAppointments).setOnClickListener {
            val intent = Intent(this, AppointmentsActivity::class.java)
            startActivity(intent)
        }

        // Emergency Contacts
        findViewById<MaterialButton>(R.id.btnEmergencyContacts).setOnClickListener {
            val intent = Intent(this, EmergencyContactsActivity::class.java)
            startActivity(intent)
        }

        // Daily Check-In
        findViewById<MaterialButton>(R.id.btnCheckIn).setOnClickListener {
            val intent = Intent(this, DailyCheckInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showComingSoon(feature: String) {
        Toast.makeText(
            this,
            "$feature screen coming next",
            Toast.LENGTH_SHORT
        ).show()
    }
}