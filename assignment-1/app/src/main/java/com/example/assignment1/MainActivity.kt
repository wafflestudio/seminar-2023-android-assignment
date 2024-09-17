package com.example.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<TextView>(R.id.login)
        val Id = findViewById<EditText>(R.id.Id)
        val password = findViewById<EditText>(R.id.password)

        login.setOnClickListener{
           val pass = password.text.toString()

            if(pass.length<5){
                Toast.makeText(this,"유효하지 않은 비밀번호입니다.",Toast.LENGTH_SHORT).show()
            }

            else{
                val intent = Intent(this,SecondActivity::class.java)
                intent.putExtra("Id",Id.text.toString())
                intent.putExtra("password",password.text.toString())
                startActivity(intent)
            }
        }
    }
}