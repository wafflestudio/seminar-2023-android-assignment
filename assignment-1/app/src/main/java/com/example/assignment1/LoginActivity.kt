package com.example.assignment1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameText = intent.getStringExtra("username")
        val passwordText = intent.getStringExtra("password")

        val textViewUserinfo : TextView = findViewById(R.id.textViewUserInfo)
        val textViewUserId : TextView = findViewById(R.id.textViewUserId)
        val textViewUserPw : TextView = findViewById(R.id.textViewUserPw)
        textViewUserId.text = intent.getStringExtra("username")
        textViewUserPw.text = intent.getStringExtra("password")
        val textViewAnswer : TextView = findViewById(R.id.textViewAnswer)
        val textViewOperator : TextView = findViewById(R.id.textViewOperator)

        val commonCalculator = View.OnClickListener {
            val num1 : Int = findViewById<EditText>(R.id.editTextInputNumber1).text.toString().toIntOrNull() ?: 0
            val num2 : Int = findViewById<EditText>(R.id.editTextInputNumber2).text.toString().toIntOrNull() ?: 0
            textViewOperator.text = (it as Button).text.toString()
            textViewAnswer.text = when (it.text.toString()) {
                "+" -> (num1+num2).toString()
                "-" -> (num1-num2).toString()
                "*" -> (num1*num2).toString()
                "/" -> {
                    if (num2==0) "Error"
                    else (num1/num2).toString()
                }
                else -> "Error"
            }

        }

        val buttons = listOf<Button>(
            findViewById(R.id.buttonAdd),
            findViewById(R.id.buttonSubtract),
            findViewById(R.id.buttonMultiply),
            findViewById(R.id.buttonDivide)
        )

        buttons.forEach { it.setOnClickListener(commonCalculator) }

    }
}