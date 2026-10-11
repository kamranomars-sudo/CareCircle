package com.example.carecircle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Care Profile
        findViewById<MaterialButton>(R.id.btnCareProfile).setOnClickListener {
            val intent = Intent(this, CareProfileActivity::class.java)
            startActivity(intent)
        }

        // Care Tasks
        findViewById<MaterialButton>(R.id.btnTasks).setOnClickListener {
            val intent = Intent(this, TasksActivity::class.java)
            startActivity(intent)
        }

        // Appointments
        findViewById<MaterialButton>(R.id.btnAppointments).setOnClickListener {
            val intent = Intent(this, AppointmentsActivity::class.java)
            startActivity(intent)
        }

        // Emergency Contacts
        findViewById<MaterialButton>(R.id.btnEmergency).setOnClickListener {
            val intent = Intent(this, EmergencyContactsActivity::class.java)
            startActivity(intent)
        }

        // About
        findViewById<MaterialButton>(R.id.btnAbout).setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}