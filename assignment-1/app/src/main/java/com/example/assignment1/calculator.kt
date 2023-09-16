package com.example.assignment1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach


class calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        //id와 pw 받아오기
        val userId = intent.getStringExtra("id")
        val userPw = intent.getStringExtra("pw")

        val textView1 = findViewById<TextView>(R.id.id)
        textView1.text = userId

        val textView2 = findViewById<TextView>(R.id.pw)
        textView2.text = userPw

        //사칙연산 버튼 클릭 이벤트
        val gridLayout = findViewById<GridLayout>(R.id.button)

        gridLayout.forEach { view ->
            val button = view as Button
            val buttonText = button.text.toString()

            view.setOnClickListener {
                val operationTextView = findViewById<TextView>(R.id.operation)
                operationTextView.text = buttonText

                val editText1 = findViewById<EditText>(R.id.firstNum)
                val firstNum = editText1.text.toString().toInt()

                val editText2 = findViewById<EditText>(R.id.secondNum)
                val secondNum = editText2.text.toString().toInt()

                val answerTextView = findViewById<TextView>(R.id.answer)
                val answer : Int

                when(buttonText) {
                    "+" -> answer = firstNum + secondNum
                    "-" -> answer = firstNum - secondNum
                    "*" -> answer = firstNum * secondNum
                    else -> answer = firstNum / secondNum
                }

                answerTextView.text = answer.toString()
            }
        }
    }
}