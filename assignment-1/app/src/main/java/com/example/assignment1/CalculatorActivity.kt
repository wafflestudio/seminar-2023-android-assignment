package com.example.assignment1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val firstNum = findViewById<EditText>(R.id.first_number_input)
        val secondNum = findViewById<EditText>(R.id.second_number_input)
        val resultTextView = findViewById<TextView>(R.id.result)

        val gridLayout = findViewById<GridLayout>(R.id.button)

        gridLayout.forEach { view ->
            if (view is Button) {
                view.setOnClickListener { button ->
                    val operator = (button as Button).text.toString()
                    calculateResult(firstNum, secondNum, resultTextView, operator)
                }
            }
        }
    }

    private fun calculateResult(firstNum: EditText, secondNum: EditText, resultTextView: TextView, operator: String) {
        val num1 = firstNum.text.toString().toIntOrNull() ?: return
        val num2 = secondNum.text.toString().toIntOrNull() ?: return

        val result = when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            else -> num1 / num2
        }

        resultTextView.text = result.toString()
    }
}