package com.example.assignment2

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.get
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.BoardViewBinding
import com.example.assignment2.databinding.ButtonTextBinding
import com.example.assignment2.databinding.TurnNumberBinding

class BoardHistoryAdapter(private val context: Context, private val viewModel: MainViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<BoardData>? = mutableListOf()

    inner class TurnNumberViewHolder(private val binding: TurnNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BoardData.TurnNumber) {
            val turnNumber = item.num.toString()
            binding.turnNumberText.text = turnNumber + "턴"
        }
    }

    inner class BoardValueViewHolder(private val binding: BoardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BoardData.BoardValue) {
            val board = item.board
            var index = 0
            binding.boardView.children.forEach { child ->
                if (child is TextView) {
                    child.text = board[index].toString()
                    index++
                }
            }

        }
    }

    inner class ButtonTextViewHolder(private val binding: ButtonTextBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BoardData.ButtonText) {
            val btnText = item.btnText
            binding.buttonTextView.text = btnText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("BHA", "items:${items}")
        return when (viewType) {

            BoardData.ViewType.TURN_NUMBER.ordinal -> {
                val binding = TurnNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TurnNumberViewHolder(binding)
            }

            BoardData.ViewType.BOARD_VALUE.ordinal -> {
                val binding = BoardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BoardValueViewHolder(binding)
            }

            BoardData.ViewType.BUTTON_TEXT.ordinal -> {
                val binding = ButtonTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ButtonTextViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is TurnNumberViewHolder -> {
                val item = items!![position] as BoardData.TurnNumber
                holder.bind(item)
            }
            is BoardValueViewHolder -> {
                val item = items!![position] as BoardData.BoardValue
                holder.bind(item)
            }
            is ButtonTextViewHolder -> {
                val item = items!![position] as BoardData.ButtonText
                holder.bind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = items!![position]
        return when (item) {
            is BoardData.TurnNumber -> BoardData.ViewType.TURN_NUMBER.ordinal
            is BoardData.BoardValue -> BoardData.ViewType.BOARD_VALUE.ordinal
            is BoardData.ButtonText -> BoardData.ViewType.BUTTON_TEXT.ordinal
        }
    }

    fun setItems(newItems: MutableList<BoardData>?) {
        items = newItems
        notifyDataSetChanged()
    }

}



