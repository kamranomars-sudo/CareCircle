package com.example.carecircle

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class DailyCheckInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_check_in)

        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(R.id.btnSaveCheckIn).setOnClickListener {
            Toast.makeText(
                this,
                "Daily check-in saved",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}