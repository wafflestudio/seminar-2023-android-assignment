package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val userIdTextView = findViewById<TextView>(R.id.UserId)
        userIdTextView.text = intent.getStringExtra("Id")
        val userPwTextView = findViewById<TextView>(R.id.UserPw)
        userPwTextView.text = intent.getStringExtra("Pw")

        val plusOperator = findViewById<TextView>(R.id.plus)
        val minusOperator = findViewById<TextView>(R.id.minus)
        val multiplyOperator = findViewById<TextView>(R.id.multiply)
        val divideOperator = findViewById<TextView>(R.id.divide)
        val operator = findViewById<TextView>(R.id.operator)
        val result = findViewById<TextView>(R.id.result)
        val firstInput = findViewById<EditText>(R.id.firstInput)
        val secondInput = findViewById<EditText>(R.id.secondInput)
        plusOperator.setOnClickListener {
            operator.text = "+"
            result.text = (firstInput.text.toString().toInt() + secondInput.text.toString().toInt()).toString()
        }
        minusOperator.setOnClickListener {
            operator.text = "-"
            result.text = (firstInput.text.toString().toInt() - secondInput.text.toString().toInt()).toString()
        }
        multiplyOperator.setOnClickListener {
            operator.text = "*"
            result.text = (firstInput.text.toString().toInt() * secondInput.text.toString().toInt()).toString()
        }
        divideOperator.setOnClickListener {
            operator.text = "/"
            result.text = (firstInput.text.toString().toInt() / secondInput.text.toString().toInt()).toString()
        }

    }
}