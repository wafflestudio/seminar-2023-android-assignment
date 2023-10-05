package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding :ActivityMainBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}