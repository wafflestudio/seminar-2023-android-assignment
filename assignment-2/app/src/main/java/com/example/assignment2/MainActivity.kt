package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.ActivityMainBinding
import com.example.assignment2.BoardCellAdapter
import com.example.assignment2.databinding.GridItemBinding


// Main Activity는 VMMV 에서 View를 담당

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val adapter = BoardCellAdapter(this, viewModel)
        binding.gameTable.adapter = adapter
        val recAdapter = BoardHistoryAdapter(this, viewModel)
        Log.d("View", "$recAdapter")


        binding.recyclerViewDrawer.setAdapter(recAdapter)
        binding.recyclerViewDrawer.layoutManager = LinearLayoutManager(this)


        binding.btnOpen.setOnClickListener() {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.btnInit.setOnClickListener() {
            viewModel.initGame()
        }

        viewModel.data.observe(this, { updatedData ->
            // LiveData가 업데이트될 때마다 호출되는 부분
            binding.mainTitle.text = viewModel.getTitle()
            val list = updatedData[viewModel.getTurn()][1].map { cell ->
                when (cell) {
                    0 -> ' '
                    1 -> 'O'
                    2 -> 'X'
                    else -> ' ' // 예외 처리 - 유효하지 않은 경우 공백 문자
                }
            }
        })

        viewModel.boardData.observe(this, { updatedData ->
            Log.d("View","호출")
            recAdapter.setItems(viewModel.getBoardDatas())
            val items = updatedData.toMutableList()
        })

    }






}