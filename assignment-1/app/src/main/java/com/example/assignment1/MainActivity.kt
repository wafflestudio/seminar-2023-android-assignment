package com.example.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Button
import android.content.Intent
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView : ImageView = findViewById(R.id.profileImage)
        imageView.setImageResource(R.drawable.profileimage)

        val textViewInfo: TextView? = null
        textViewInfo?.text = "이름: 손영준\nMBTI: INTJ\n좋아하는 것: 헤헤"

        val editTextUsername : EditText = findViewById(R.id.editTextUsername)
        val editTextPassword : EditText = findViewById(R.id.editTextPassword)
        val buttonLogin : Button =  findViewById(R.id.buttonLogin)


        buttonLogin.setOnClickListener {
            val textUserName = editTextUsername.text.toString()
            val textPassword = editTextPassword.text.toString()

            if (textPassword.length < 5) {
                Toast.makeText(this, "유효하지 않은 비밀번호입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nextIntent = Intent(this, LoginActivity::class.java)
            nextIntent.apply {
                this.putExtra("username", textUserName)
                this.putExtra("password", textPassword)
            }
            startActivity(nextIntent)

        }
    }
}