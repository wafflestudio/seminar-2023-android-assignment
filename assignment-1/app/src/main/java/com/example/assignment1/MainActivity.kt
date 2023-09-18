package com.example.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<TextView>(R.id.login)

        login.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }
    }
}