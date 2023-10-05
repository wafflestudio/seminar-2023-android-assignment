package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hamburgerMenu.setOnClickListener {
            binding.root.openDrawer(binding.drawer)
        }
        binding.initBtn.setOnClickListener {

        }
        binding.gameBoard.adapter = BoardAdapter(this, viewModel)

        viewModel.state.observe(this){
            binding.turnInformation.text = when(it){
                State.GAMEOVER -> "게임 오버"
                State.PLAYER1 -> "O의 차례입니다"
                State.PLAYER2 -> "X의 차례입니다"
            }
        }






    }



}

