package com.example.assignment2

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.BoardDataBinding
import com.example.assignment2.databinding.CommentBinding
import com.example.assignment2.databinding.TurnNumBinding
import org.w3c.dom.Text

//되돌아가기 기능을 구현하기 위해서, Adapter에 viewModel을 인수로 전달했는데,
//MVVM 패턴이 깨지는게 아닌지 걱정입니다.
//adapter도 mainactivity랑 똑같이 뷰라고 생각해서, 모델의 직접적인 데이터만 조작하지 않고,
//조작을 일어나게 하는 함수를 호출하면 MVVM이 지켜지는걸까요?
class HistoryAdapter(private val context : Context,private val data: List<HistoryData>, private val viewModel: MainViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    inner class TurnNumViewHolder(private val binding: TurnNumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryData.TurnNum) {
            binding.num.text = item.num.toString() + "턴"
            binding.goBack.setOnClickListener {
                viewModel.goBack(item.num)
            }
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
    inner class CommentViewHolder(private val binding: CommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryData.Comment) {
            binding.comment.text = item.comment.toString()
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when(data[position]) {
            is HistoryData.BoardData -> return HistoryData.ViewType.BOARD_DATA.ordinal
            is HistoryData.TurnNum -> return HistoryData.ViewType.TURN_NUM.ordinal
            is HistoryData.Comment -> return HistoryData.ViewType.COMMENT.ordinal
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
            is CommentViewHolder ->{
                val item = data[position] as HistoryData.Comment
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

            HistoryData.ViewType.COMMENT.ordinal -> {
                val binding = CommentBinding.inflate(inflater, parent, false)
                CommentViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

}