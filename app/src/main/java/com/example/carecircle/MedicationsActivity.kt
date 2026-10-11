package com.example.carecircle

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class MedicationsActivity : AppCompatActivity() {

    private lateinit var savedMedicationCard: MaterialCardView
    private lateinit var tvSavedMedicationName: TextView
    private lateinit var tvSavedMedicationDosage: TextView
    private lateinit var tvSavedMedicationDetails: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medications)

        savedMedicationCard = findViewById(R.id.savedMedicationCard)
        tvSavedMedicationName = findViewById(R.id.tvSavedMedicationName)
        tvSavedMedicationDosage = findViewById(R.id.tvSavedMedicationDosage)
        tvSavedMedicationDetails = findViewById(R.id.tvSavedMedicationDetails)

        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<MaterialButton>(R.id.btnAddMedication)
            .setOnClickListener {

                val intent =
                    Intent(this, AddEditMedicationActivity::class.java)

                startActivity(intent)
            }
    }

    override fun onResume() {
        super.onResume()

        loadSavedMedication()
    }

    private fun loadSavedMedication() {

        val preferences =
            getSharedPreferences("CareCirclePrefs", MODE_PRIVATE)

        val name =
            preferences.getString("medication_name", null)

        val dosage =
            preferences.getString("medication_dosage", "")

        val time =
            preferences.getString("medication_time", "")

        val instructions =
            preferences.getString("medication_instructions", "")

        if (!name.isNullOrEmpty()) {

            savedMedicationCard.visibility = View.VISIBLE

            tvSavedMedicationName.text = name

            tvSavedMedicationDosage.text = dosage

            if (instructions.isNullOrEmpty()) {
                tvSavedMedicationDetails.text = time
            } else {
                tvSavedMedicationDetails.text =
                    "$time  •  $instructions"
            }

        } else {

            savedMedicationCard.visibility = View.GONE
        }
    }
}