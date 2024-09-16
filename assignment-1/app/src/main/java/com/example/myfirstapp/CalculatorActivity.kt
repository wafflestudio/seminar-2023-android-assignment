package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        // Get user information and print Id, Pw.
        val bundle = intent.extras
        val id = bundle?.getString("id") ?: ""
        val pw = bundle?.getString("pw") ?: ""

        val textViewId = findViewById<TextView>(R.id.outputId)
        val textViewPw = findViewById<TextView>(R.id.outputPw)
        textViewId.text = id
        textViewPw.text = pw

        // Change the operator displayed as user clicks operator buttons.
        // Then, Get first operand and second operand.
        // Also, calculate and display result.
        val plusButton = findViewById<Button>(R.id.plusButton)
        val minusButton = findViewById<Button>(R.id.minusButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)
        val operator = findViewById<TextView>(R.id.operator)
        val result = findViewById<TextView>(R.id.result)
        plusButton.setOnClickListener{
            val firstOperand = findViewById<EditText>(R.id.firstOperand).text.toString().toInt()
            val secondOperand = findViewById<EditText>(R.id.secondOperand).text.toString().toInt()
            operator.text = "+"
            result.text = (firstOperand + secondOperand).toString() }
        minusButton.setOnClickListener{
            val firstOperand = findViewById<EditText>(R.id.firstOperand).text.toString().toInt()
            val secondOperand = findViewById<EditText>(R.id.secondOperand).text.toString().toInt()
            operator.text = "-"
            result.text = (firstOperand - secondOperand).toString() }
        multiplyButton.setOnClickListener{
            val firstOperand = findViewById<EditText>(R.id.firstOperand).text.toString().toInt()
            val secondOperand = findViewById<EditText>(R.id.secondOperand).text.toString().toInt()
            operator.text = "*"
            result.text = (firstOperand * secondOperand).toString() }
        divideButton.setOnClickListener{
            val firstOperand = findViewById<EditText>(R.id.firstOperand).text.toString().toInt()
            val secondOperand = findViewById<EditText>(R.id.secondOperand).text.toString().toInt()
            operator.text = "/"
            result.text = (firstOperand / secondOperand).toString() }








    }
}