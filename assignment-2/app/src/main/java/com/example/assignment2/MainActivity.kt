package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyMultiAdapter(viewModel.data)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val cells = listOf(
            binding.block00, binding.block01, binding.block02,
            binding.block10, binding.block11, binding.block12,
            binding.block20, binding.block21, binding.block22
        )

        viewModel.board.observe(this){ board ->
            board.forEachIndexed{ index, state ->
                    if (state == null) cells[index].text = " "
                    else if (state) cells[index].text = "O"
                    else cells[index].text = "X"
            }
        }

        viewModel.resetText.observe(this){
            binding.reset.text = it
        }

        viewModel.statusText.observe(this){
            binding.status.text = it
        }

        //SetOnClickListener - menu
        binding.menu.setOnClickListener{
            binding.root.openDrawer(binding.drawer)
        }

        cells.forEachIndexed{ idx, cell ->
            cell.setOnClickListener {
                viewModel.boardClick(idx)
                adapter.notifyItemChanged(viewModel.record.lastIndex)
            }
        }

        //SetOnClickListener - reset
        binding.reset.setOnClickListener {
            viewModel.reset()
            
            //recycler View 초기화
            val newData = mutableListOf<MyMultiData>(MyMultiData.TypeB("게임 시작!"))
            viewModel.data  = newData
            adapter.setData(newData)
            adapter.notifyDataSetChanged()
        }
    }
}