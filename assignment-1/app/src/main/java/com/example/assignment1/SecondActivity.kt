package com.example.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val Id = intent.getStringExtra("Id")
        val password = intent.getStringExtra("password")

        val displayId = findViewById<TextView>(R.id.idView)
        val displayPassword = findViewById<TextView>(R.id.passwordView)

        displayId.text = "$Id"
        displayPassword.text = "$password"

        val plus = findViewById<TextView>(R.id.plus)
        val minus = findViewById<TextView>(R.id.minus)
        val multiply = findViewById<TextView>(R.id.multiply)
        val divide = findViewById<TextView>(R.id.divide)

        val firstNum = findViewById<EditText>(R.id.firstNum)
        val secondNum = findViewById<EditText>(R.id.secondNum)
        val operator = findViewById<TextView>(R.id.operator)
        val result = findViewById<TextView>(R.id.result)

        plus.setOnClickListener{
            val num1 = firstNum.text.toString().toIntOrNull() ?: 0
            val num2 = secondNum.text.toString().toIntOrNull() ?: 0
            operator.text = "+"
            result.text = (num1 + num2).toString()
        }

        minus.setOnClickListener {
            val num1 = firstNum.text.toString().toIntOrNull() ?: 0
            val num2 = secondNum.text.toString().toIntOrNull() ?: 0
            operator.text = "-"
            result.text = (num1 - num2).toString()
        }

        multiply.setOnClickListener {
            val num1 = firstNum.text.toString().toIntOrNull() ?: 0
            val num2 = secondNum.text.toString().toIntOrNull() ?: 0
            operator.text = "*"
            result.text = (num1 * num2).toString()
        }

        divide.setOnClickListener {
            val num1 = firstNum.text.toString().toIntOrNull() ?: 0
            val num2 = secondNum.text.toString().toIntOrNull() ?: 0
            operator.text = "/"
            result.text = (num1 / num2).toString()
        }
    }
}