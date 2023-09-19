package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButtonView = findViewById<TextView>(R.id.loginButton)
        val usernameTextView = findViewById<EditText>(R.id.username)
        val passwordTextView = findViewById<EditText>(R.id.password)

        loginButtonView.setOnClickListener {
            val passwordText = passwordTextView.text.toString()
            if (passwordText.length < 5) {
                Toast.makeText(this, "유효하지 않은 비밀번호입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val usernameText = usernameTextView.text.toString()

            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("username", usernameText)
                putExtra("password", passwordText)
            }
            startActivity(intent)
        }
    }
}