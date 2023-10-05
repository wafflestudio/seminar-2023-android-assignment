package com.example.assignment2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.BoardValueBinding
import com.example.assignment2.databinding.ButtonTextBinding
import com.example.assignment2.databinding.NavItemBinding
import com.example.assignment2.databinding.TurnNumberBinding

class BoardHistoryAdapter(private val context: Context, private val items: List<BoardData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class TurnNumberViewHolder(private val binding: TurnNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BoardData.TurnNumber) {
            val turnNumber = item.num
        }
    }

    inner class BoardValueViewHolder(private val binding: BoardValueBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BoardData.BoardValue) {
            val board = item.board
        }
    }

    inner class ButtonTextViewHolder(private val binding: ButtonTextBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BoardData.ButtonText) {
            val btnText = item.btnText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {

            BoardData.ViewType.TURN_NUMBER.ordinal -> {
                val binding = TurnNumberBinding.inflate(inflater, parent, false)
                TurnNumberViewHolder(binding)
            }

            BoardData.ViewType.BOARD_VALUE.ordinal -> {
                val binding = BoardValueBinding.inflate(inflater, parent, false)
                BoardValueViewHolder(binding)
            }

            BoardData.ViewType.BUTTON_TEXT.ordinal -> {
                val binding = ButtonTextBinding.inflate(inflater, parent, false)
                ButtonTextViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is TurnNumberViewHolder -> {
                val item = items[position] as BoardData.TurnNumber
                holder.bind(item)
            }
            is BoardValueViewHolder -> {
                val item = items[position] as BoardData.BoardValue
                holder.bind(item)
            }
            is ButtonTextViewHolder -> {
                val item = items[position] as BoardData.ButtonText
                holder.bind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType.ordinal
    }

}



