package com.vision.bmi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var weight: EditText
    lateinit var heightFeet: EditText
    lateinit var heightInch: EditText
    lateinit var calculateButton: Button
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weight = findViewById(R.id.edtWeight)
        heightFeet = findViewById(R.id.edtHeightFeet)
        heightInch = findViewById(R.id.edtHeightInch)
        calculateButton = findViewById(R.id.btnCalculate)
        result = findViewById(R.id.txtResult)
        calculateButton.setOnClickListener {
            showResult()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showResult() {

        val weightValue = weight.text.toString().toDoubleOrNull()
        val feetValue = heightFeet.text.toString().toIntOrNull()
        val inchValue = heightInch.text.toString().toIntOrNull()

        if (weightValue != null && feetValue != null && inchValue != null) {
            val totalHeight = (feetValue * 12) + inchValue
            val bmi = calculateBMI(weightValue, totalHeight)
            val bmiCategory = categorizeBMI(bmi)

            result.text = "BMI: $bmi\nCategory: $bmiCategory"
        } else {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateBMI(weight: Double, height: Int): Double {
        // Convert height to meters
        val heightInMeters = height * 0.0254
        // Calculate BMI using the formula: weight / (height^2)
        return weight / (heightInMeters * heightInMeters)
    }

    private fun categorizeBMI(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Normal weight"
            bmi < 30 -> "Overweight"
            else -> "Obese"
        }
    }
}
