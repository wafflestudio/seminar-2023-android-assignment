package com.example.assignment2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.TypeFinishViewBinding
import com.example.assignment2.databinding.TypeMainViewBinding
import com.example.assignment2.databinding.TypeStartViewBinding

class MyMultiAdapter( private val items: List<GameState>, private val goBack: (Int) -> Unit ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position].turn) {
            0 -> MyMultiData.ViewType.START.ordinal
            else -> when(items[position].progress.isFinished()) {
                true -> MyMultiData.ViewType.FINISH.ordinal
                else -> MyMultiData.ViewType.MAIN.ordinal
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MyMultiData.ViewType.START.ordinal -> { TypeStartViewHolder(TypeStartViewBinding.inflate(LayoutInflater.from(parent.context))) }
            MyMultiData.ViewType.MAIN.ordinal -> { TypeMainViewHolder(TypeMainViewBinding.inflate(LayoutInflater.from(parent.context))) }
            MyMultiData.ViewType.FINISH.ordinal -> { TypeFinishViewHolder(TypeFinishViewBinding.inflate(LayoutInflater.from(parent.context))) }
            else -> throw IllegalStateException("Invalid ViewType") // This doesn't happen.
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = items[position]
        when (holder) {
            is TypeStartViewHolder -> {}
            is TypeMainViewHolder -> { holder.bind( MyMultiData.Main(data), goBack ) }
            is TypeFinishViewHolder -> { holder.bind( MyMultiData.Finish(data) ) }
        }
    }

    class TypeStartViewHolder(binding: TypeStartViewBinding): RecyclerView.ViewHolder(binding.root)

    class TypeMainViewHolder(private val binding: TypeMainViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyMultiData.Main, goBack: (Int) -> Unit) {
            val turn = data.gameState.turn
            val board = data.gameState.board
            binding.turn.text = "${turn}턴"
            binding.gridLayout.forEachIndexed { idx, button ->
                (button as TextView).text = board[idx/3][idx%3].symbol()
                button.setBackgroundColor(board[idx/3][idx%3].color())
            }
            binding.back.setOnClickListener { goBack(turn) }
        }
    }

    class TypeFinishViewHolder(private val binding: TypeFinishViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyMultiData.Finish) {
            val board = data.gameState.board
            val progress = data.gameState.progress
            binding.gridLayout.forEachIndexed { idx, button ->
                (button as TextView).text = board[idx/3][idx%3].symbol()
                (button).setBackgroundColor(board[idx/3][idx%3].color())
            }
            binding.result.text = progress.toString()
        }
    }

}



/*
package com.example.assignment2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.TypeFinishViewBinding
import com.example.assignment2.databinding.TypeMainViewBinding
import com.example.assignment2.databinding.TypeStartViewBinding

class MyMultiAdapter( private val items: List<GameState>, private val goBack: (Int) -> Unit ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MyMultiData.Start -> MyMultiData.ViewType.START.ordinal
            is MyMultiData.Main -> MyMultiData.ViewType.MAIN.ordinal
            is MyMultiData.Finish -> MyMultiData.ViewType.FINISH.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MyMultiData.ViewType.START.ordinal -> { TypeStartViewHolder(TypeStartViewBinding.inflate(LayoutInflater.from(parent.context))) }
            MyMultiData.ViewType.MAIN.ordinal -> { TypeMainViewHolder(TypeMainViewBinding.inflate(LayoutInflater.from(parent.context))) }
            MyMultiData.ViewType.FINISH.ordinal -> { TypeFinishViewHolder(TypeFinishViewBinding.inflate(LayoutInflater.from(parent.context))) }
            else -> throw IllegalStateException("Invalid ViewType") // This doesn't happen.
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = items[position]
        when (holder) {
            is TypeStartViewHolder -> {}
            is TypeMainViewHolder -> { holder.bind( data as MyMultiData.Main, goBack ) }
            is TypeFinishViewHolder -> { holder.bind(data as MyMultiData.Finish) }
        }
    }

    class TypeStartViewHolder(binding: TypeStartViewBinding): RecyclerView.ViewHolder(binding.root)

    class TypeMainViewHolder(private val binding: TypeMainViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyMultiData.Main, goBack: (Int) -> Unit) {
            val turn = data.gameState.turn
            val board = data.gameState.board
            binding.turn.text = "${turn}턴"
            binding.gridLayout.forEachIndexed { idx, button ->
                (button as TextView).text = board[idx/3][idx%3].symbol()
                button.setBackgroundColor(board[idx/3][idx%3].color())
            }
            binding.back.setOnClickListener { goBack(turn) }
        }
    }

    class TypeFinishViewHolder(private val binding: TypeFinishViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MyMultiData.Finish) {
            val board = data.gameState.board
            val progress = data.gameState.progress
            binding.gridLayout.forEachIndexed { idx, button ->
                (button as TextView).text = board[idx/3][idx%3].symbol()
                (button).setBackgroundColor(board[idx/3][idx%3].color())
            }
            binding.result.text = progress.toString()
        }
    }

}
 */