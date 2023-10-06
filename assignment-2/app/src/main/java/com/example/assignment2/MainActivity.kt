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


        //basic set for Restart OnCreate
        viewModel.basicSet(0,0,binding.block00)
        viewModel.basicSet(0,1,binding.block01)
        viewModel.basicSet(0,2,binding.block02)
        viewModel.basicSet(1,0,binding.block10)
        viewModel.basicSet(1,1,binding.block11)
        viewModel.basicSet(1,2,binding.block12)
        viewModel.basicSet(2,0,binding.block20)
        viewModel.basicSet(2,1,binding.block21)
        viewModel.basicSet(2,2,binding.block22)

        //SetOnClickListener - menu
        binding.menu.setOnClickListener{
            binding.root.openDrawer(binding.drawer)
        }

        //SetOnClickListener - block
        binding.block00.setOnClickListener {
            viewModel.boardClick(0,0, binding.block00, binding.reset, binding.status)
            adapter.notifyItemChanged(viewModel.data.lastIndex)
        }

        binding.block01.setOnClickListener {
            viewModel.boardClick(0,1, binding.block01, binding.reset, binding.status)
            adapter.notifyItemChanged(viewModel.data.lastIndex)
        }

        binding.block02.setOnClickListener {
            viewModel.boardClick(0,2, binding.block02, binding.reset, binding.status)
            adapter.notifyItemChanged(viewModel.data.lastIndex)
        }

        binding.block10.setOnClickListener {
            viewModel.boardClick(1,0, binding.block10, binding.reset, binding.status)
            adapter.notifyItemChanged(viewModel.data.lastIndex)
        }

        binding.block11.setOnClickListener {
            viewModel.boardClick(1,1, binding.block11, binding.reset, binding.status)
            adapter.notifyItemChanged(viewModel.data.lastIndex)
        }

        binding.block12.setOnClickListener {
            viewModel.boardClick(1,2, binding.block12, binding.reset, binding.status)
            adapter.notifyItemChanged(viewModel.data.lastIndex)
        }

        binding.block20.setOnClickListener {
            viewModel.boardClick(2,0, binding.block20, binding.reset, binding.status)
            adapter.notifyItemChanged(viewModel.data.lastIndex)
        }

        binding.block21.setOnClickListener {
            viewModel.boardClick(2,1, binding.block21, binding.reset, binding.status)
            adapter.notifyItemChanged(viewModel.data.lastIndex)
        }

        binding.block22.setOnClickListener {
            viewModel.boardClick(2,2, binding.block22, binding.reset, binding.status)
            adapter.notifyItemChanged(viewModel.data.lastIndex)
        }

        //SetOnClickListener - reset
        binding.reset.setOnClickListener {
            viewModel.team = true
            viewModel.isEnd = false
            viewModel.record = Array(9){Array(3) { Array(3) { null } }}
            viewModel.board = Array(3) { Array(3) { null } }
            viewModel.count = 0

            binding.block00.text = getString(R.string.empty)
            binding.block01.text = getString(R.string.empty)
            binding.block02.text = getString(R.string.empty)
            binding.block10.text = getString(R.string.empty)
            binding.block11.text = getString(R.string.empty)
            binding.block12.text = getString(R.string.empty)
            binding.block20.text = getString(R.string.empty)
            binding.block21.text = getString(R.string.empty)
            binding.block22.text = getString(R.string.empty)

            binding.reset.text = getString(R.string.reset)

            val newData = mutableListOf<MyMultiData>(MyMultiData.TypeB("게임 시작!"))
            viewModel.data  = newData
            adapter.setData(newData)
            adapter.notifyDataSetChanged()

        }
    }
}