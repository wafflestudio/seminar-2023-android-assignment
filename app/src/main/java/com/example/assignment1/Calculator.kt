package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_layout)

        val intent: Intent = intent
        val receivedid = intent.getStringExtra("id")
        val receivedpw = intent.getStringExtra("pw")
        var idView = findViewById<TextView>(R.id.ID)
        var pwView = findViewById<TextView>(R.id.PW)
        idView.text = receivedid
        pwView.text = receivedpw

        val plusbutton = findViewById<TextView>(R.id.plus)
        val minusbutton = findViewById<TextView>(R.id.minus)
        val multibutton = findViewById<TextView>(R.id.multi)
        val divbutton = findViewById<TextView>(R.id.div)
        val num1= findViewById<EditText>(R.id.first).text
        val num2= findViewById<EditText>(R.id.second).text

        val oper = findViewById<TextView>(R.id.operator)
        var result = findViewById<TextView>(R.id.result)

        plusbutton.setOnClickListener{
            if(num1.length == 0 || num2.length == 0 ) {
                val message = "숫자를 입력하세요"
                val duration = Toast.LENGTH_LONG
                val toast = Toast.makeText(applicationContext, message, duration)
                toast.show()
            }
            else {
                oper.text = "+"
                val res = num1.toString().toFloat() + num2.toString().toFloat()
                result.text = res.toInt().toString()
            }
        }

        minusbutton.setOnClickListener{
            if(num1.length == 0 || num2.length == 0 ) {
                val message = "숫자를 입력하세요"
                val duration = Toast.LENGTH_LONG
                val toast = Toast.makeText(applicationContext, message, duration)
                toast.show()
            }
            else {
                oper.text = "-"
                val res = num1.toString().toFloat() - num2.toString().toFloat()
                result.text = res.toInt().toString()
            }
        }

        multibutton.setOnClickListener{
            if(num1.length == 0 || num2.length == 0 ) {
                val message = "숫자를 입력하세요"
                val duration = Toast.LENGTH_LONG
                val toast = Toast.makeText(applicationContext, message, duration)
                toast.show()
            }
            else {
                oper.text = "*"
                val res = num1.toString().toFloat() * num2.toString().toFloat()
                result.text = res.toInt().toString()
            }
        }

        divbutton.setOnClickListener{
            if(num1.length == 0 || num2.length == 0 ) {
                val message = "숫자를 입력하세요"
                val duration = Toast.LENGTH_LONG
                val toast = Toast.makeText(applicationContext, message, duration)
                toast.show()
            }
            else {
                oper.text = "/"
                val res = num1.toString().toFloat() / num2.toString().toFloat()
                result.text = res.toInt().toString()
            }
        }
    }
}