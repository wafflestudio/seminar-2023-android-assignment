package com.example.assignment2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = HistoryAdapter(
            list =  viewModel.history,
            onClickItem = { idx, count ->
                viewModel.goToPast(idx)
                binding.historyRecyclerView.adapter?.notifyItemRangeRemoved(idx, count)
            }
        )
        binding.historyRecyclerView.adapter = adapter
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(this)

        val cells = listOf(
            binding.cell1, binding.cell2, binding.cell3,
            binding.cell4, binding.cell5, binding.cell6,
            binding.cell7, binding.cell8, binding.cell9,
        )
        cells.forEachIndexed { idx, cell ->
            cell.setOnClickListener {
                viewModel.handleClick(idx).let { validClick ->
                    if (validClick) adapter.notifyItemChanged(viewModel.history.lastIndex)
                }
            }
        }

        viewModel.board.observe(this) { board ->
            board.forEachIndexed { index, state ->
                cells[index].text = state.text
            }

            val gameState = getGameStateFromBoard(board)
            setRetryButtonText(gameState)
            setTurnText(gameState)
        }

        binding.retryButton.setOnClickListener {
            viewModel.retry().let { removed ->
                adapter.notifyItemRangeRemoved(0, removed)
            }
        }
        binding.openDrawer.setOnClickListener {
            binding.root.openDrawer(binding.drawer)
        }
    }

    private fun setRetryButtonText(gameState: GameState) {
        binding.retryButton.text = gameState.buttonText
        binding.retryButton.setBackgroundColor(getColor(gameState.buttonColorRes))
    }

    private fun setTurnText(gameState: GameState) {
        binding.turnText.text = gameState.titleText
    }
}