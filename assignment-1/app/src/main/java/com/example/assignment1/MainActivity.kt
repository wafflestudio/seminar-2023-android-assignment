package com.example.assignment1

import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameEditText = findViewById<EditText>(R.id.username_edit_text)
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
        val loginButton = findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener{
            val username=usernameEditText.text.toString()
            val password=passwordEditText.text.toString()
            val intent = Intent(this,CalculatorActivity::class.java)
            intent.putExtra("Username",username)
            intent.putExtra("Password",password)
            startActivity(intent)
        }
    }
}