package com.example.assignment2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.BoardItemViewBinding
import com.example.assignment2.databinding.EndItemViewBinding
import com.example.assignment2.databinding.StartItemViewBinding

private enum class ViewType {
    Start, Board, End,
}

class HistoryAdapter(
    private val list: List<List<CellState>>,
    private val onClickItem: (idx: Int, count: Int, List<CellState>) -> Unit,
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
                StartItemViewBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    )
                )
            )

            ViewType.Board -> BoardViewHolder(
                BoardItemViewBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    )
                )
            )

            ViewType.End -> EndViewHolder(EndItemViewBinding.inflate(LayoutInflater.from(parent.context)))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position]

        when (holder) {
            is BoardViewHolder -> holder.bind(position, data)
            is EndViewHolder -> holder.bind(data)
        }
    }

    inner class StartViewHolder(binding: StartItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class BoardViewHolder(private val binding: BoardItemViewBinding) :
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
            binding.turnNum.text = "${turnNum}턴"
            binding.root.setOnClickListener {
                if (position != list.lastIndex) {
                    onClickItem(position + 1, list.size - position, board)
                }
            }
        }
    }

    inner class EndViewHolder(private val binding: EndItemViewBinding) :
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

            when (getGameStateFromBoard(board)) {
                GameState.Over -> {
                    val winner = if ((board.count { it != CellState.E } % 2) == 0) "X" else "O"
                    binding.winner.text = "${winner}의 승리!!"
                    if (winner == "O") {
                        binding.root.background = binding.root.context.getDrawable(R.drawable.round_rect_pink)
                    } else {
                        binding.root.background = binding.root.context.getDrawable(R.drawable.round_rect_blue)
                    }
                }
                GameState.Draw -> {
                    binding.winner.text = "무승부!!"
                    binding.root.background = binding.root.context.getDrawable(R.drawable.round_rect_purple)
                }
                else -> {}
            }
        }
    }
}