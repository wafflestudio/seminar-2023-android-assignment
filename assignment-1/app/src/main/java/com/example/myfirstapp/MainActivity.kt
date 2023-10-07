package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.logIn)
        button.setOnClickListener{
            val id = findViewById<EditText>(R.id.InputId).text.toString()  // Id got from <EditText> inputId.
            val pw = findViewById<EditText>(R.id.inputPw).text.toString()  // Password got from <EditText> inputPw.

            if (pw.length < 5) { // If pwLength is less than 5, toast message appears.
                val message = "유효하지 않은 비밀번호입니다."
                val duration = Toast.LENGTH_SHORT // 또는 Toast.LENGTH_LONG
                val toast = Toast.makeText(applicationContext, message, duration)
                toast.show()
            } else { // Only if pwLength is greater than or equal to 5, CalculatorActivity starts.
                val intent = Intent(this, CalculatorActivity::class.java)
                val bundle = Bundle()
                bundle.putString("id", id)
                bundle.putString("pw", pw)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
}