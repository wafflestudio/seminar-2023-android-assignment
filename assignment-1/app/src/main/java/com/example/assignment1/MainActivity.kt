package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener(View.OnClickListener {
            val editText1 = findViewById<EditText>(R.id.id)
            val userId = editText1.text.toString()

            val editText2 = findViewById<EditText>(R.id.pw)
            val userPw = editText2.text.toString()

            if(userPw.length>=5) {
                val intent = Intent(this, calculator::class.java)
                intent.putExtra("id", userId)
                intent.putExtra("pw", userPw)
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext,"유효하지 않은 비밀번호입니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}