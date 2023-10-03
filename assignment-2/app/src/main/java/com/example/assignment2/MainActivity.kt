package com.example.assignment2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리셋 버튼
        binding.reset.setOnClickListener {
            viewModel.reset()
        }

        // 메뉴 버튼
        binding.menuBar.setOnClickListener {
            binding.root.openDrawer(binding.drawer)
        }

        // 틱택토 버튼
        binding.ticTacToePlayground.forEach { view ->
            val textView = view as TextView
            view.setOnClickListener {
                viewModel.handleClickEvent(binding, textView) ?: return@setOnClickListener
            }
        }

        val items = viewModel.getAllDrawerGameViewData().toMutableList()

        // 턴 변경시 조치
        viewModel.turnIndex.observe(this) {
            val gameViewData = viewModel.getGameViewData()
            binding.title.text = gameViewData.titleText
            binding.reset.text = gameViewData.resetText
            setTicTacToeButton(gameViewData.playground)
            val delSize = items.size - it
            if (delSize > 0) {
                for (i in 0 until delSize) {
                    items.removeLast()
                }
                binding.gameHistoryRecyclerView.adapter?.notifyItemRangeRemoved(it, delSize)
            } else {
                if (it > 0) {
                    items.add(viewModel.getDrawerGameViewData())
                    binding.gameHistoryRecyclerView.adapter?.notifyItemChanged(it - 1)
                }
            }
        }

        /*=======Drawer=======*/
        val adapter = GameHistoryAdapter(items, viewModel)
        binding.gameHistoryRecyclerView.adapter = adapter
        binding.gameHistoryRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setTicTacToeButton(playground: Map<Int, String>) {
        for (i in 0..8) {
            val textView = getTicTacToeTextView(i)
            textView.text = playground[i] ?: ""
        }
    }

    private fun getTicTacToeTextView(number: Int): TextView = when (number) {
        0 -> binding.ticTacToe0
        1 -> binding.ticTacToe1
        2 -> binding.ticTacToe2
        3 -> binding.ticTacToe3
        4 -> binding.ticTacToe4
        5 -> binding.ticTacToe5
        6 -> binding.ticTacToe6
        7 -> binding.ticTacToe7
        8 -> binding.ticTacToe8
        else -> throw IllegalStateException("Invalid number")
    }

}