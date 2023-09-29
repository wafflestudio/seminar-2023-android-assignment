package com.example.assignment2

import android.content.Context
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
    data class GameBoard(val num: Int) : MyMultiData(ViewType.Board)
    data class GameEnd(val winner: Int) : MyMultiData(ViewType.End)
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
            is MyMultiData.GameEnd -> MyMultiData.ViewType.End.ordinal
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
            MyMultiData.ViewType.End.ordinal -> TypeCViewHolder(
                GameEndItemViewBinding.inflate(LayoutInflater.from(parent.context))
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
            is TypeCViewHolder -> {
                holder.bind(data as MyMultiData.GameEnd)
            }
        }

    }

    inner class TypeAViewHolder(private val binding: GameStartItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyMultiData.GameStart) { }
    }
    inner class TypeBViewHolder(private val binding: GameBoardItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyMultiData.GameBoard) { }
    }
    inner class TypeCViewHolder(private val binding: GameEndItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyMultiData.GameEnd) { }

    }

}