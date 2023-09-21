package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<TextView>(R.id.log_in_button)
        button.setOnClickListener {
            val id = findViewById<EditText>(R.id.id).text.toString()
            val pw = findViewById<EditText>(R.id.password).text.toString()
            if(pw.length < 5) {
                val message = "유효하지 않은 비밀번호입니다"
                val duration = Toast.LENGTH_LONG
                val toast = Toast.makeText(applicationContext, message, duration)
                toast.show()
            }
            else{
                val intent = Intent(this, Calculator::class.java)
                intent.putExtra("id",id)
                intent.putExtra("pw",pw)
                startActivity(intent)
            }
        }

    }
}