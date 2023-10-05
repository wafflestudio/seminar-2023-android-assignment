package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import com.example.assignment2.databinding.ActivityMainBinding
import com.example.assignment2.BoardCellAdapter


// Main Activity는 VMMV 에서 View를 담당

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = listOf("O", "O", "O", "O", "O", "O", "O", "O", "O")
        val adapter = BoardCellAdapter<String>(this, data)

        binding.btnOpen.setOnClickListener() {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.btnInit.setOnClickListener() {
            // gameTable 어댑터 초기화 -> 동적 뷰
        }
        binding.gameTable.adapter = adapter

    }






}