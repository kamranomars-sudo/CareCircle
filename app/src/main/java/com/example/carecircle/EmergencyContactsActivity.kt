package com.example.carecircle

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EmergencyContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_contacts)

        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}