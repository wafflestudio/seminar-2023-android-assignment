package com.example.assignment2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding
import com.example.assignment2.utils.combine

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = HistoryAdapter(
            viewModel.history,
            onClickItem = { idx, count, board ->
                viewModel.goToPast(idx, board)
                binding.historyRecyclerView.adapter?.notifyItemRangeRemoved(idx, count)
            }
        )

        val cells = listOf(
            binding.cell1, binding.cell2, binding.cell3,
            binding.cell4, binding.cell5, binding.cell6,
            binding.cell7, binding.cell8, binding.cell9,
        )

        cells.forEachIndexed { idx, cell ->
            cell.setOnClickListener {
                viewModel.handleClick(idx).let { valid ->
                    if (valid) adapter.notifyItemChanged(viewModel.history.lastIndex)
                }
            }
        }

        combine(
            viewModel.board, viewModel.gameState
        ).observe(this) { (board, gameState) ->
            if (gameState == GameState.O || gameState == GameState.X) {
                board.forEachIndexed { index, state ->
                    cells[index].text = state.text
                }
            }
            setRetryButton(gameState)
            setTurnText(gameState)
        }

        binding.retryButton.setOnClickListener {
            viewModel.retry()
            adapter.notifyDataSetChanged()
        }

        binding.openDrawer.setOnClickListener {
            binding.root.openDrawer(binding.drawer)
        }

        binding.historyRecyclerView.adapter = adapter
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setRetryButton(gameState: GameState) {
        binding.retryButton.text = gameState.buttonText
        binding.retryButton.setBackgroundColor(getColor(gameState.buttonColorRes))
    }

    private fun setTurnText(gameState: GameState) {
        binding.turnText.text = gameState.titleText
    }
}