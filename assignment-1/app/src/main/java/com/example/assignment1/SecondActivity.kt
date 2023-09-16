package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<TextView>(R.id.UserId).text = intent.getStringExtra("Id")
        findViewById<TextView>(R.id.UserPw).text = intent.getStringExtra("Pw")

        val plusOperator = findViewById<TextView>(R.id.plus)
        val minusOperator = findViewById<TextView>(R.id.minus)
        val multiplyOperator = findViewById<TextView>(R.id.multiply)
        val divideOperator = findViewById<TextView>(R.id.divide)
        val operator = findViewById<TextView>(R.id.operator)
        val result = findViewById<TextView>(R.id.result)
        val firstInput = findViewById<EditText>(R.id.firstInput)
        val secondInput = findViewById<EditText>(R.id.secondInput)

        val inputIsFilled: () -> Boolean = {
            val isFilled = firstInput.text.toString().trim().isNotEmpty() && secondInput.text.toString().trim().isNotEmpty()
            if (!isFilled) {
                Toast.makeText(this, "값을 모두 입력해주세요", Toast.LENGTH_SHORT).run {
                    setGravity(Gravity.CENTER, 0, 0)
                    show()
                }
            }
            isFilled
        }

        plusOperator.setOnClickListener {
            operator.text = "+"
            if(inputIsFilled()) {
                result.text = (firstInput.text.toString().toInt() + secondInput.text.toString().toInt()).toString()
            }
        }
        minusOperator.setOnClickListener {
            operator.text = "-"
            if(inputIsFilled()) {
                result.text = (firstInput.text.toString().toInt() - secondInput.text.toString().toInt()).toString()
            }
        }
        multiplyOperator.setOnClickListener {
            operator.text = "*"
            if(inputIsFilled()) {
                result.text = (firstInput.text.toString().toInt() * secondInput.text.toString().toInt()).toString()
            }
        }
        divideOperator.setOnClickListener {
            operator.text = "/"
            if(inputIsFilled()) {
                result.text = (firstInput.text.toString().toInt() / secondInput.text.toString().toInt()).toString()
            }
        }

    }
}