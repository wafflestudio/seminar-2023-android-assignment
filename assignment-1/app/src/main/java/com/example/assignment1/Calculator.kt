package com.example.assignment1

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Calculator: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cal)

        findViewById<TextView>(R.id.id).text = intent.getStringExtra("id")
        findViewById<TextView>(R.id.pw).text = intent.getStringExtra("pw")

        val input1 = findViewById<EditText>(R.id.input1)
        val input2 = findViewById<EditText>(R.id.input2)

        val operator = findViewById<TextView>(R.id.operator)
        val output = findViewById<TextView>(R.id.output)

        val plus = findViewById<TextView>(R.id.plus)
        val minus = findViewById<TextView>(R.id.minus)
        val multiply = findViewById<TextView>(R.id.multiply)
        val divide = findViewById<TextView>(R.id.divide)

        plus.setOnClickListener{
            val num1 = input1.text.toString().toFloat()
            val num2 = input2.text.toString().toFloat()
            operator.text = "+"
            output.text = (num1 + num2).toString()
        }
        minus.setOnClickListener{
            val num1 = input1.text.toString().toFloat()
            val num2 = input2.text.toString().toFloat()
            operator.text = "-"
            output.text = (num1 - num2).toString()
        }
        multiply.setOnClickListener{
            val num1 = input1.text.toString().toFloat()
            val num2 = input2.text.toString().toFloat()
            operator.text = "*"
            output.text = (num1 * num2).toString()
        }
        divide.setOnClickListener{
            val num1 = input1.text.toString().toFloat()
            val num2 = input2.text.toString().toFloat()
            operator.text = "/"
            output.text = (num1 / num2).toString()
        }
    }
}