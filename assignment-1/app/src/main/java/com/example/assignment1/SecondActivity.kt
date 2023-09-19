package com.example.assignment1

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val usernameText = intent.getStringExtra("username")
        val passwordText = intent.getStringExtra("password")

        val usernameTextView = findViewById<TextView>(R.id.username)
        val passwordTextView = findViewById<TextView>(R.id.password)
        usernameTextView.text = usernameText
        passwordTextView.text = passwordText

        val plusOperatorView = findViewById<TextView>(R.id.plus)
        val minusOperatorView = findViewById<TextView>(R.id.minus)
        val multiplyOperatorView = findViewById<TextView>(R.id.multiply)
        val dividerOperatorView = findViewById<TextView>(R.id.divider)

        val firstOperandView = findViewById<EditText>(R.id.firstOperand)
        val operatorView = findViewById<TextView>(R.id.operator)
        val secondOperandView = findViewById<EditText>(R.id.secondOperand)
        val resultView = findViewById<TextView>(R.id.result)

        plusOperatorView.setOnClickListener {
            operatorView.text = "+"
            val firstOperand = firstOperandView.text.toString().toIntOrNull()
            val secondOperand = secondOperandView.text.toString().toIntOrNull()
            if (firstOperand == null || secondOperand == null) {
                return@setOnClickListener
            }
            resultView.text = runCatching {
                firstOperand + secondOperand
            }.getOrElse { return@setOnClickListener }.toString()
        }

        minusOperatorView.setOnClickListener {
            operatorView.text = "-"
            val firstOperand = firstOperandView.text.toString().toIntOrNull()
            val secondOperand = secondOperandView.text.toString().toIntOrNull()
            if (firstOperand == null || secondOperand == null) {
                return@setOnClickListener
            }
            resultView.text = runCatching {
                firstOperand - secondOperand
            }.getOrElse { return@setOnClickListener }.toString()
        }

        multiplyOperatorView.setOnClickListener {
            operatorView.text = "*"
            val firstOperand = firstOperandView.text.toString().toIntOrNull()
            val secondOperand = secondOperandView.text.toString().toIntOrNull()
            if (firstOperand == null || secondOperand == null) {
                return@setOnClickListener
            }
            resultView.text = runCatching {
                firstOperand * secondOperand
            }.getOrElse { return@setOnClickListener }.toString()
        }

        dividerOperatorView.setOnClickListener {
            operatorView.text = "/"
            val firstOperand = firstOperandView.text.toString().toIntOrNull()
            val secondOperand = secondOperandView.text.toString().toIntOrNull()
            if (firstOperand == null || secondOperand == null) {
                return@setOnClickListener
            }
            resultView.text = runCatching {
                firstOperand / secondOperand
            }.getOrElse { return@setOnClickListener }.toString()
        }

    }
}