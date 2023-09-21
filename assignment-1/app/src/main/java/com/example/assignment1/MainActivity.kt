package com.example.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second) //로그인
        val textView =  findViewById<TextView>(R.id.loginbutton)

        textView.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val idraw =  findViewById<EditText>(R.id.id)
            val pwraw = findViewById<EditText>(R.id.pw)
            val id = idraw.text.toString()
            val pw = pwraw.text.toString()
            intent.putExtra("id",id)
            intent.putExtra("pw",pw)
            startActivity(intent)
        }


    }

}

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //계산기
        val receivedIntent = intent
        val id = receivedIntent.getStringExtra("id")
        val pw = receivedIntent.getStringExtra("pw")

        //info
        val info = findViewById<TextView>(R.id.info)
        info.text = "id: $id \n pw: $pw"
        val first = findViewById<EditText>(R.id.firstnum)
        val sec = findViewById<EditText>(R.id.secondnum)
        var op = findViewById<TextView>(R.id.operator)
        var result = findViewById<TextView>(R.id.result)

        val plus = findViewById<TextView>(R.id.plus)
        val minus = findViewById<TextView>(R.id.minus)
        val mul = findViewById<TextView>(R.id.multiply)
        val div = findViewById<TextView>(R.id.divide)

        plus.setOnClickListener {
            op.text = "+"
            val firstt = first.text.toString()
            val firstint = firstt.toInt()
            val sectext = sec.text.toString()
            val secondint = sectext.toInt()
            val resultint = firstint + secondint
            result.text =  resultint.toString()
        }

        minus.setOnClickListener {
            op.text = "-"
            val firstt = first.text.toString()
            val firstint = firstt.toInt()
            val sectext = sec.text.toString()
            val secondint = sectext.toInt()
            val resultint = firstint - secondint
            result.text =  resultint.toString()
        }

        mul.setOnClickListener {
            op.text = "*"
            val firstt = first.text.toString()
            val firstint = firstt.toInt()
            val sectext = sec.text.toString()
            val secondint = sectext.toInt()
            val resultint = firstint * secondint
            result.text =  resultint.toString()
        }
        div.setOnClickListener {
            op.text = "/"
            val firstt = first.text.toString()
            val firstint = firstt.toInt()
            val sectext = sec.text.toString()
            val secondint = sectext.toInt()
            val resultint = firstint / secondint
            result.text =  resultint.toString()
        }
    }
}