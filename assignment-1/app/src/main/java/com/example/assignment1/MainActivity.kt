package com.example.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginTextView = findViewById<Button>(R.id.loginButton)
        loginTextView.setOnClickListener {
            val id = findViewById<EditText>(R.id.inputId).getText()
            val pw = findViewById<EditText>(R.id.inputPassword).getText()
            Toast.makeText(this, "유dddddddd.", Toast.LENGTH_SHORT)
            if (pw.length < 5) {
                Toast.makeText(this, "유효하지 않은 비밀번호입니다.", Toast.LENGTH_SHORT)
            }
            else {
                val intent = Intent(this, CalculatorActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)
                startActivity(intent)
            }
        }
    }
}