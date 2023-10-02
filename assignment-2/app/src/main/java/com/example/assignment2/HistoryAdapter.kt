package com.example.assignment2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.GameBoardItemViewBinding
import com.example.assignment2.databinding.GameEndItemViewBinding
import com.example.assignment2.databinding.GameStartItemViewBinding

sealed class MyMultiData(val viewType: ViewType) {
    data class GameStart(val num: Int) : MyMultiData(ViewType.Start)
    data class GameBoard(val nTurn: Int?, val gameEnd: Boolean, val boardInfo: IntArray?) : MyMultiData(ViewType.Board)
    enum class ViewType { Start, Board, End }
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
                holder.bind(data as MyMultiData.GameStart)
            }
            is TypeBViewHolder -> {
                holder.bind(data as MyMultiData.GameBoard)
            }
        }

    }

    inner class TypeAViewHolder(private val binding: GameStartItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyMultiData.GameStart) { }
    }
    inner class TypeBViewHolder(private val binding: GameBoardItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        private fun teamToString(team: Int, data: MyMultiData.GameBoard): String {
            return MainActivity().teamToString(data.boardInfo?.get(team)?:0)
        }
        fun bind(data: MyMultiData.GameBoard) {
            if (data.gameEnd) {
                binding.textTurnNum.text = ""
                binding.boardBackground.setBackgroundColor(Color.parseColor("#43FFA0A0"))
                binding.button.text = MainActivity().statusToString()
            }

            binding.textTurnNum.text = data.nTurn.toString() + "턴"
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