package com.example.assignment2

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.BoardDataBinding
import com.example.assignment2.databinding.TurnNumBinding
import org.w3c.dom.Text

class HistoryAdapter(private val context : Context,private val data: List<HistoryData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    inner class TurnNumViewHolder(private val binding: TurnNumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryData.TurnNum) {
            binding.num.text = item.num.toString() + "턴"
        }
    }
    inner class BoardDataViewHolder(private val binding: BoardDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryData.BoardData) {
            binding.miniBoard.removeAllViews()
            for(i in 0 until 9){
                val cell = TextView(context)
                cell.text = when(item.board[i]){
                    Mark.PLAYER1 -> "O"
                    Mark.PLAYER2 -> "X"
                    Mark.EMPTY -> ""
                }
                cell.textSize = 30f
                cell.width = 150
                cell.height = 150
                cell.textAlignment = View.TEXT_ALIGNMENT_CENTER
                cell.gravity = Gravity.CENTER
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(10,10,10,10)
                cell.layoutParams = layoutParams
                cell.setBackgroundColor(Color.GRAY)

                binding.miniBoard.addView(cell)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]) {
            is HistoryData.BoardData -> return HistoryData.ViewType.BOARD_DATA.ordinal
            is HistoryData.TurnNum -> return HistoryData.ViewType.TURN_NUM.ordinal
            else -> -1
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