package com.example.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import android.widget.Toast

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        val idTextView = findViewById<TextView>(R.id.id)
        val pwTextView = findViewById<TextView>(R.id.password)
        idTextView.text = intent.getStringExtra("intentId")
        pwTextView.text = intent.getStringExtra("intentPw")

        val answerTextView = findViewById<TextView>(R.id.answer)
        val operatorTextView = findViewById<TextView>(R.id.operator)
        fun operatorButton(str: String, operation: (Int, Int) -> Int) {
            val num1 = findViewById<EditText>(R.id.inputNum1).getText().toString().toInt()
            val num2 = findViewById<EditText>(R.id.inputNum2).getText().toString().toInt()
            operatorTextView.text = str
            answerTextView.text = operation(num1, num2).toString()
        }

        findViewById<Button>(R.id.buttonPlus).setOnClickListener {
            operatorButton("+") { a, b -> a + b };
        }
        findViewById<Button>(R.id.buttonMinus).setOnClickListener {
            operatorButton("-") { a, b -> a - b };
        }
        findViewById<Button>(R.id.buttonMult).setOnClickListener {
            operatorButton("*") { a, b -> a * b };
        }
        findViewById<Button>(R.id.buttonDiv).setOnClickListener {
            operatorButton("/") { a, b -> a / b };
        }
    }
}