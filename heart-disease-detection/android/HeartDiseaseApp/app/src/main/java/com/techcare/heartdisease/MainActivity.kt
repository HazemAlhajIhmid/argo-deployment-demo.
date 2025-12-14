package com.techcare.heartdisease

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.techcare.heartdisease.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        // Display statistics
        binding.apply {
            accuracyKnn.text = "82%"
            accuracyNb.text = "82%"
            accuracyDt.text = "70%"
            recordsCount.text = "303"
            modelsCount.text = "3"
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            // Risk Calculator Card
            cardRiskCalculator.setOnClickListener {
                startActivity(Intent(this@MainActivity, RiskCalculatorActivity::class.java))
            }

            // About Project Card
            cardAbout.setOnClickListener {
                startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            }

            // Model Comparison Card
            cardModels.setOnClickListener {
                // Navigate to model comparison screen
                // startActivity(Intent(this@MainActivity, ModelComparisonActivity::class.java))
            }

            // Start Assessment Button
            btnStartAssessment.setOnClickListener {
                startActivity(Intent(this@MainActivity, RiskCalculatorActivity::class.java))
            }
        }
    }
}
