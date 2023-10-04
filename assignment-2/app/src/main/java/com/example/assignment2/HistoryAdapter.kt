package com.example.assignment2

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.GameBoardItemViewBinding
import com.example.assignment2.databinding.GameStartItemViewBinding


sealed class MyMultiData(val viewType: ViewType) {
    data class GameStart(val num: Int) : MyMultiData(ViewType.Start)
    data class GameBoard(val nTurn: Int?, val gameStatus: MainActivity.Status, val boardInfo: IntArray?) : MyMultiData(ViewType.Board)
    enum class ViewType { Start, Board }
}
class HistoryAdapter(
    private val list: List<MyMultiData>,
    private val context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = list.size
    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is MyMultiData.GameStart -> MyMultiData.ViewType.Start.ordinal
            is MyMultiData.GameBoard -> MyMultiData.ViewType.Board.ordinal
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MyMultiData.ViewType.Start.ordinal -> TypeAViewHolder(
                GameStartItemViewBinding.inflate(LayoutInflater.from(parent.context))
            )
            MyMultiData.ViewType.Board.ordinal -> TypeBViewHolder(
                GameBoardItemViewBinding.inflate(LayoutInflater.from(parent.context))
            )
            else -> throw IllegalStateException("Invalid ViewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position]
        when (holder) {
            is TypeAViewHolder -> {
                holder.bind()
            }
            is TypeBViewHolder -> {
                holder.bind(data as MyMultiData.GameBoard)
            }
        }

    }

    inner class TypeAViewHolder(private val binding: GameStartItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() { }
    }
    inner class TypeBViewHolder(private val binding: GameBoardItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        private fun teamToString(team: Int, data: MyMultiData.GameBoard): String {
            return when (data.boardInfo?.get(team) ?: -1) {
                0 -> ""
                1 -> "O"
                2 -> "X"
                else -> "ERROR"
            }
        }
        fun bind(data: MyMultiData.GameBoard) {
            if (data.gameStatus == MainActivity.Status.CONTINUE) {
                binding.textTurnNum.text = data.nTurn.toString() + "턴"
                binding.boardBackground.background = ContextCompat.getDrawable(context, com.example.assignment2.R.drawable.round_square)
                binding.button.text = "되돌아가기"
            }
            else {
                binding.textTurnNum.text = ""
                binding.boardBackground.background = ContextCompat.getDrawable(context, com.example.assignment2.R.drawable.red_round_square)
                binding.button.text = when (data.gameStatus) {
                    MainActivity.Status.O_WIN -> "O의 승리!!"
                    MainActivity.Status.X_WIN -> "X의 승리!!"
                    MainActivity.Status.DRAW -> "무승부!!"
                    else -> "ERROR"
                }
            }
            binding.board1.text = teamToString(1, data)
            binding.board2.text = teamToString(2, data)
            binding.board3.text = teamToString(3, data)
            binding.board4.text = teamToString(4, data)
            binding.board5.text = teamToString(5, data)
            binding.board6.text = teamToString(6, data)
            binding.board7.text = teamToString(7, data)
            binding.board8.text = teamToString(8, data)
            binding.board9.text = teamToString(9, data)
        }
    }

}