package com.example.assignment2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding
interface MainActivityCallback {
    fun onAdapterClickEvent(positionStart: Int, itemCount: Int)
}
class MainActivity : AppCompatActivity(), MainActivityCallback{
    private lateinit var adapter: HistoryAdapter
    private lateinit var gridLayout: GridLayout
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()
    override fun onAdapterClickEvent(positionStart: Int, itemCount: Int) {
        adapter.notifyDataSetChanged()
        adapter.notifyItemRangeRemoved(positionStart, itemCount)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = HistoryAdapter(mainViewModel,this, mainViewModel.historyData, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.cell1.setOnClickListener{
            if(mainViewModel.clickWhoWins(1)) {
                gridLayout = binding.board
                mainViewModel.clickAddData(gridLayout)
                adapter.notifyItemChanged(mainViewModel.historyData.lastIndex)
            }
        }
        binding.cell2.setOnClickListener{
            if(mainViewModel.clickWhoWins(2)) {
                gridLayout = binding.board
                mainViewModel.clickAddData(gridLayout)
                adapter.notifyItemChanged(mainViewModel.historyData.lastIndex)
            }
        }
        binding.cell3.setOnClickListener{
            if(mainViewModel.clickWhoWins(3)) {
                gridLayout = binding.board
                mainViewModel.clickAddData(gridLayout)
                adapter.notifyItemChanged(mainViewModel.historyData.lastIndex)
            }
        }
        binding.cell4.setOnClickListener{
            if(mainViewModel.clickWhoWins(4)) {
                gridLayout = binding.board
                mainViewModel.clickAddData(gridLayout)
                adapter.notifyItemChanged(mainViewModel.historyData.lastIndex)
            }
        }
        binding.cell5.setOnClickListener{
            if(mainViewModel.clickWhoWins(5)) {
                gridLayout = binding.board
                mainViewModel.clickAddData(gridLayout)
                adapter.notifyItemChanged(mainViewModel.historyData.lastIndex)
            }
        }
        binding.cell6.setOnClickListener{
            if(mainViewModel.clickWhoWins(6)) {
                gridLayout = binding.board
                mainViewModel.clickAddData(gridLayout)
                adapter.notifyItemChanged(mainViewModel.historyData.lastIndex)
            }
        }
        binding.cell7.setOnClickListener{
            if(mainViewModel.clickWhoWins(7)) {
                gridLayout = binding.board
                mainViewModel.clickAddData(gridLayout)
                adapter.notifyItemChanged(mainViewModel.historyData.lastIndex)
            }
        }
        binding.cell8.setOnClickListener{
            if(mainViewModel.clickWhoWins(8)) {
                gridLayout = binding.board
                mainViewModel.clickAddData(gridLayout)
                adapter.notifyItemChanged(mainViewModel.historyData.lastIndex)
            }
        }
        binding.cell9.setOnClickListener{
            if(mainViewModel.clickWhoWins(9)) {
                gridLayout = binding.board
                mainViewModel.clickAddData(gridLayout)
                adapter.notifyItemChanged(mainViewModel.historyData.lastIndex)
            }
        }
        binding.drawerButton.setOnClickListener{
            binding.root.openDrawer(binding.drawer)
        }
        binding.resetButton.setOnClickListener{
            mainViewModel.resetData()
            adapter.notifyDataSetChanged()
        }
        mainViewModel.boardLiveData.observe(this){
            when(it[0][0]){
                0 -> binding.cell1.text = ""
                1 -> binding.cell1.text = "O"
                -1 -> binding.cell1.text = "X"
            }
            when(it[0][1]){
                0 -> binding.cell2.text = ""
                1 -> binding.cell2.text = "O"
                -1 -> binding.cell2.text = "X"
            }
            when(it[0][2]){
                0 -> binding.cell3.text = ""
                1 -> binding.cell3.text = "O"
                -1 -> binding.cell3.text = "X"
            }
            when(it[1][0]){
                0 -> binding.cell4.text = ""
                1 -> binding.cell4.text = "O"
                -1 -> binding.cell4.text = "X"
            }
            when(it[1][1]){
                0 -> binding.cell5.text = ""
                1 -> binding.cell5.text = "O"
                -1 -> binding.cell5.text = "X"
            }
            when(it[1][2]){
                0 -> binding.cell6.text = ""
                1 -> binding.cell6.text = "O"
                -1 -> binding.cell6.text = "X"
            }
            when(it[2][0]){
                0 -> binding.cell7.text = ""
                1 -> binding.cell7.text = "O"
                -1 -> binding.cell7.text = "X"
            }
            when(it[2][1]){
                0 -> binding.cell8.text = ""
                1 -> binding.cell8.text = "O"
                -1 -> binding.cell8.text = "X"
            }
            when(it[2][2]){
                0 -> binding.cell9.text = ""
                1 -> binding.cell9.text = "O"
                -1 -> binding.cell9.text = "X"
            }

        }
        mainViewModel.infoText.observe(this){
            binding.info.text = it.toString()
        }
        mainViewModel.resetText.observe(this){
            binding.resetButton.text = it.toString()
        }
        mainViewModel.resetColor.observe(this){
            binding.resetButton.setBackgroundColor(Color.parseColor(it))
        }




    }
}