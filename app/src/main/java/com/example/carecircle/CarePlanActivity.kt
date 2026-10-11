package com.example.carecircle

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CarePlanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_care_plan)

        val btnBack = findViewById<TextView>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }
    }
}