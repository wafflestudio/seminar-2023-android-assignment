package com.example.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener{
            val id = findViewById<EditText>(R.id.id).text.toString()
            val pw = findViewById<EditText>(R.id.pw).text.toString()

            if (pw.length < 5){
                val text = "유효하지 않은 비밀번호입니다."
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(this, text, duration)
                toast.show()
            }
            else {
                val intent: Intent = Intent(this, Calculator::class.java)
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)
                startActivity(intent)
            }
        }
    }
}


