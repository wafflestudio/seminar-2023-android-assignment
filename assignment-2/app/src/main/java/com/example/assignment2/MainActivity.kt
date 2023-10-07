package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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

        val adapter = BoardAdapter(this, viewModel)
        binding.gameBoard.adapter = adapter

        binding.initBtn.setOnClickListener {
            viewModel.init()
        }

        viewModel.board.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })
        viewModel.state.observe(this){
            binding.turnInformation.text = when(it){
                State.GAMEOVER -> "게임 오버"
                State.DRAW -> "무승부"
                State.PLAYER1 -> "O의 차례입니다"
                State.PLAYER2 -> "X의 차례입니다"
            }
            binding.initBtn.text = when(it){
                State.GAMEOVER, State.DRAW -> "한판더"
                else -> "초기화"
            }
        }


        val historyAdapter = HistoryAdapter(this, viewModel.history.value!!)
        binding.recyclerView.adapter = historyAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.history.observe(this, Observer {
            historyAdapter.notifyDataSetChanged()

        })




    }



}

