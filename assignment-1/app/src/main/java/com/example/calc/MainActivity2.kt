package com.example.calc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val intent: Intent = intent
        val userId = intent.getStringExtra("userId")
        val userPw = intent.getStringExtra("userPw")
        val idText=findViewById<TextView>(R.id.ID)
        idText.text=userId
        val PwText=findViewById<TextView>(R.id.Pw)
        PwText.text=userPw

        var num1=findViewById<EditText>(R.id.num1)
        var num2=findViewById<EditText>(R.id.num2)
        val oper=findViewById<TextView>(R.id.oper)
        val resu=findViewById<TextView>(R.id.result)
        var r:Float
        val outBtn=findViewById<Button>(R.id.logOut)
        outBtn.setOnClickListener(View.OnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)

        })

        val plusBtn=findViewById<ImageButton>(R.id.plus)
        plusBtn.setOnClickListener(View.OnClickListener {
            oper.text="+"
            r=num1.text.toString().toFloat()+num2.text.toString().toFloat()
            resu.text="=$r"

        })
        val minusBtn=findViewById<ImageButton>(R.id.minus)
        minusBtn.setOnClickListener(View.OnClickListener {
            oper.text="-"
            r=num1.text.toString().toFloat()-num2.text.toString().toFloat()
            resu.text="=$r"

        })
        val mulBtn=findViewById<ImageButton>(R.id.mul)
        mulBtn.setOnClickListener(View.OnClickListener {
            oper.text="*"
            r=num1.text.toString().toFloat()*num2.text.toString().toFloat()
            resu.text="=$r"

        })
        val divBtn=findViewById<ImageButton>(R.id.div)
        divBtn.setOnClickListener(View.OnClickListener {
            oper.text="/"
            r=num1.text.toString().toFloat()/num2.text.toString().toFloat()
            resu.text="=$r"

        })
    }
}