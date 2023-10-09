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
        setupBinding()
        setupRecyclerView()
        setupButtons()
        observeViewModelChanges()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupRecyclerView() {
        val adapter = createTurnAdapter()
        binding.recyclerViewTurns.adapter = adapter
        binding.recyclerViewTurns.layoutManager = LinearLayoutManager(this)
    }

    private fun createTurnAdapter(): TurnAdapter {
        return TurnAdapter(
            viewModel.history,
            cellClickListener = { idx, _, board ->
                viewModel.goToPast(idx + 1, board)
                binding.recyclerViewTurns.adapter?.notifyItemRangeRemoved(idx + 1, viewModel.history.size - (idx + 1))
            }
        )
    }

    private fun setupButtons() {
        val buttons = listOf(
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )

        buttons.forEachIndexed { idx, cell ->
            cell.setOnClickListener { handleCellClick(idx) }
        }

        binding.resetButton.setOnClickListener { handleResetButtonClick() }
        binding.openDrawerButton.setOnClickListener { triggerDrawerOpen() }
    }

    private fun handleCellClick(index: Int) {
        viewModel.handleClick(index).let { valid ->
            if (valid) (binding.recyclerViewTurns.adapter as? TurnAdapter)?.notifyItemChanged(viewModel.history.lastIndex)
        }
    }

    private fun handleResetButtonClick() {
        viewModel.retry()
        binding.recyclerViewTurns.adapter?.notifyDataSetChanged()
    }

    private fun triggerDrawerOpen() {
        binding.root.openDrawer(binding.navigationView)
    }

    private fun observeViewModelChanges() {
        combine(viewModel.board, viewModel.gameState).observe(this) { (board, gameState) ->
            reflectUIChanges(board, gameState)
        }
    }

    private fun reflectUIChanges(board: List<Cell>, gameState: Game) {
        modifyBoardUI(board, gameState)
        configureRetryButton(gameState)
        displayCurrentTurn(gameState)
    }

    private fun modifyBoardUI(board: List<Cell>, gameState: Game) {
        val buttons = listOf(
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )
        if (gameState == Game.O || gameState == Game.X) {
            board.forEachIndexed { index, state ->
                buttons[index].text = state.text
            }
        }
    }

    private fun configureRetryButton(gameState: Game) {
        binding.resetButton.text = gameState.buttonText
    }

    private fun displayCurrentTurn(gameState: Game) {
        binding.openDrawerButton.text = gameState.titleText
    }
}
