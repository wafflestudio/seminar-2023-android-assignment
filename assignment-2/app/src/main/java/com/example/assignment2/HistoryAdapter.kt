package com.example.assignment2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.BoardDataBinding
import com.example.assignment2.databinding.TurnNumBinding

class HistoryAdapter(private val data: List<HistoryData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    inner class TurnNumViewHolder(private val binding: TurnNumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryData.TurnNum) {
            binding.num.text = item.toString()
        }
    }
    inner class BoardDataViewHolder(private val binding: BoardDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryData.BoardData) {
            binding.miniBoard.text = item.toString()
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is TurnNumViewHolder -> {
                val item = data[position] as HistoryData.TurnNum
                holder.bind(item)
            }
            is BoardDataViewHolder -> {
                val item = data[position] as HistoryData.BoardData
                holder.bind(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {

            HistoryData.ViewType.TURN_NUM.ordinal -> {
                val binding = TurnNumBinding.inflate(inflater, parent, false)
                TurnNumViewHolder(binding)
            }

            HistoryData.ViewType.BOARD_DATA.ordinal -> {
                val binding = BoardDataBinding.inflate(inflater, parent, false)
                BoardDataViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }
}