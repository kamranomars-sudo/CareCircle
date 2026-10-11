package com.example.carecircle

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class AppointmentMapActivity : AppCompatActivity() {

    private lateinit var tvMapAddress: TextView
    private var appointmentAddress: String = "Hamilton Family Health Centre"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_map)

        tvMapAddress = findViewById(R.id.tvMapAddress)

        appointmentAddress =
            intent.getStringExtra("appointment_address")
                ?.takeIf { it.isNotBlank() }
                ?: "Hamilton Family Health Centre"

        tvMapAddress.text = appointmentAddress

        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(R.id.btnDirections).setOnClickListener {
            openDirections()
        }
    }

    private fun openDirections() {

        val uri = Uri.parse(
            "geo:0,0?q=${Uri.encode(appointmentAddress)}"
        )

        val intent = Intent(Intent.ACTION_VIEW, uri)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                "No maps application found",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}