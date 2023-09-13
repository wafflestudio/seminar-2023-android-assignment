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
        val passwordLength : () -> Int = { editTextPw.text.toString().length }

        val logInButton = findViewById<Button>(R.id.Button)
        logInButton.setOnClickListener{
            if( passwordLength() < 5){
                Toast.makeText(this, "유효하지 않은 비밀번호입니다", Toast.LENGTH_SHORT).run{
                    setGravity(Gravity.CENTER,0,0)
                    show()
                }
            }
            else {
                Intent(this, SecondActivity::class.java).run {
                    putExtra("Id", editTextId.text.toString())
                    putExtra("Pw", editTextPw.text.toString())
                    startActivity(this)
                }
            }
        }
    }

}