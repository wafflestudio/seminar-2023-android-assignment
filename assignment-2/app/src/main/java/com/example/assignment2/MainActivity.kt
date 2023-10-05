package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        binding.btnOpen.setOnClickListener() {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.btnInit.setOnClickListener() {
            viewModel.initBoard()
            viewModel.checkGameStatus()
        }

        viewModel.data.observe(this, Observer { updatedDataList ->
            // LiveData가 업데이트될 때마다 호출되는 부분
            adapter.notifyDataSetChanged()
            Log.d("mainActivity","data changed")
            binding.mainTitle.text = viewModel.checkGameStatus()
        })

        val recAdapter = BoardHistoryAdapter(this, viewModel.getPastDataList())
        binding.recyclerView.adapter = recAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.pastData.observe(this, Observer { updatedPastDataList ->
            // LiveData가 업데이트될 때마다 호출되는 부분
            recAdapter.notifyDataSetChanged()
            Log.d("mainActivity","rec data changed")
        })
    }






}