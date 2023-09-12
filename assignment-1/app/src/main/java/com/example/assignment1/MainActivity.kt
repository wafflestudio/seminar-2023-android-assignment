package com.example.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editTextId = findViewById<EditText>(R.id.editTextId)
        val editTextPw = findViewById<EditText>(R.id.editTextPw)
        val logInButton = findViewById<Button>(R.id.Button)
        logInButton.setOnClickListener{
            val textId = editTextId.text.toString()
            val textPw = editTextPw.text.toString()
            if(textPw.length <5){
                val toast = Toast.makeText(this, "유효하지 않은 비밀번호입니다", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
            else {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("Id", textId)
                intent.putExtra("Pw", textPw)

                startActivity(intent)
            }
        }
    }

}