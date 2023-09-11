package com.example.calc

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

        val myButton = findViewById<Button>(R.id.myButton)

        myButton.setOnClickListener(View.OnClickListener {
            val userId = findViewById<EditText>(R.id.myId).text.toString()
            val userPw = findViewById<EditText>(R.id.myPw).text.toString()
            if(userPw.length<5)
            {
                val toastMessage = "유효하지 않은 비밀번호입니다."
                val duration = Toast.LENGTH_SHORT // 또는 Toast.LENGTH_LONG

                val toast = Toast.makeText(this, toastMessage, duration)
                toast.show()
            }
            else
            {
                val nextIntent = Intent(this, MainActivity2::class.java)
                nextIntent.putExtra("userId", userId)
                nextIntent.putExtra("userPw", userPw)
                startActivity(nextIntent)
            }


        })
    }
}