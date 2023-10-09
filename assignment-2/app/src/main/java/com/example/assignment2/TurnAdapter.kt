package com.example.assignment2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.GamePlayItemViewBinding
import com.example.assignment2.databinding.GameFinishItemViewBinding
import com.example.assignment2.databinding.GameInitialItemViewBinding

enum class GameViewType {
    INITIAL, PLAY, FINISH
}

class TurnAdapter(
    private val gameBoards: List<List<Cell>>,
    private val cellClickListener: (position: Int, remaining: Int, board: List<Cell>) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        val currentBoard = gameBoards[position]
        return when {
            currentBoard.all { it == Cell.EMPTY } -> GameViewType.INITIAL
            getGameStateFromBoard(currentBoard) in listOf(Game.X, Game.O) -> GameViewType.PLAY
            else -> GameViewType.FINISH
        }.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewTypeEnum = GameViewType.values()[viewType]
        return when (viewTypeEnum) {
            GameViewType.INITIAL -> StartViewHolder(GameInitialItemViewBinding.inflate(inflater))
            GameViewType.PLAY -> BoardViewHolder(GamePlayItemViewBinding.inflate(inflater))
            GameViewType.FINISH -> EndViewHolder(GameFinishItemViewBinding.inflate(inflater))
        }
    }
    override fun getItemCount(): Int = gameBoards.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StartViewHolder -> {}
            is BoardViewHolder, is EndViewHolder -> (holder as? BoardBinder)?.bind(gameBoards[position])
        }
    }

    interface BoardBinder {
        fun bind(board: List<Cell>)
    }

    inner class StartViewHolder(binding: GameInitialItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    inner class BoardViewHolder(private val binding: GamePlayItemViewBinding) : RecyclerView.ViewHolder(binding.root), BoardBinder {
        override fun bind(board: List<Cell>) {
            bindCellViews(binding, board)
        }
    }

    inner class EndViewHolder(private val binding: GameFinishItemViewBinding) : RecyclerView.ViewHolder(binding.root), BoardBinder {
        override fun bind(board: List<Cell>) {
            bindCellViews(binding, board)
        }
    }

    private fun bindCellViews(binding: Any, board: List<Cell>) {
        val cellsBinding = when (binding) {
            is GamePlayItemViewBinding -> listOf(binding.cell1, binding.cell2, binding.cell3, binding.cell4, binding.cell5, binding.cell6, binding.cell7, binding.cell8, binding.cell9)
            is GameFinishItemViewBinding -> listOf(binding.cell1, binding.cell2, binding.cell3, binding.cell4, binding.cell5, binding.cell6, binding.cell7, binding.cell8, binding.cell9)
            else -> throw IllegalArgumentException("Unsupported binding type")
        }
        cellsBinding.forEachIndexed { index, cellView -> cellView.text = board[index].text }
    }
}