package com.example.assignment2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.BoardItemViewBinding
import com.example.assignment2.databinding.EndItemViewBinding
import com.example.assignment2.databinding.StartItemViewBinding

private enum class ViewType {
    Start, Board, End,
}

class HistoryAdapter(
    private val list: List<List<CellState>>,
    private val onClickItem: (idx: Int, count: Int) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        if (list[position].all { it == CellState.E }) return ViewType.Start.ordinal
        return when (getGameStateFromBoard(list[position])) {
            GameState.X,
            GameState.O -> ViewType.Board.ordinal

            GameState.Draw,
            GameState.Over -> ViewType.End.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ViewType.entries[viewType]) {
            ViewType.Start -> StartViewHolder(
                StartItemViewBinding.inflate(LayoutInflater.from(parent.context))
            )

            ViewType.Board -> BoardViewHolder(
                BoardItemViewBinding.inflate(LayoutInflater.from(parent.context))
            )

            ViewType.End -> EndViewHolder(
                EndItemViewBinding.inflate(LayoutInflater.from(parent.context))
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position]

        when (holder) {
            is BoardViewHolder -> holder.bind(position, data)
            is EndViewHolder -> holder.bind(data)
        }
    }

    private inner class StartViewHolder(binding: StartItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    private inner class BoardViewHolder(private val binding: BoardItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, board: List<CellState>) {
            val cells = listOf(
                binding.cell1, binding.cell2, binding.cell3,
                binding.cell4, binding.cell5, binding.cell6,
                binding.cell7, binding.cell8, binding.cell9,
            )
            board.forEachIndexed { index, cellState ->
                cells[index].text = cellState.text
            }

            val turnNum = board.count { it != CellState.E }
            binding.turnNum.text = binding.root.context.getString(R.string.turn_text, turnNum)
            binding.root.setOnClickListener {
                if (position != list.lastIndex) {
                    onClickItem(position + 1, list.size - position)
                }
            }
        }
    }

    private inner class EndViewHolder(private val binding: EndItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(board: List<CellState>) {
            val cells = listOf(
                binding.cell1, binding.cell2, binding.cell3,
                binding.cell4, binding.cell5, binding.cell6,
                binding.cell7, binding.cell8, binding.cell9,
            )
            board.forEachIndexed { index, cellState ->
                cells[index].text = cellState.text
            }

            val context = binding.root.context
            when (getGameStateFromBoard(board)) {
                GameState.Over -> {
                    val winner = if ((board.count { it != CellState.E } % 2) == 0) "X" else "O"
                    binding.winner.text = context.getString(R.string.winner_text, winner)
                    binding.root.background = AppCompatResources.getDrawable(
                        context,
                        if (winner == "O") R.drawable.round_rect_pink
                        else R.drawable.round_rect_blue
                    )
                }
                GameState.Draw -> {
                    binding.winner.text = "무승부!!"
                    binding.root.background = AppCompatResources.getDrawable(context, R.drawable.round_rect_purple)
                }
                else -> {}
            }
        }
    }
}