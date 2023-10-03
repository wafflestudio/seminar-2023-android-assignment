package com.example.assignment2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.TypeDrawerGameItemViewBinding

class GameHistoryAdapter(
    private val data: List<GameHistoryData>,
    private val viewModel: MainViewModel,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            GameHistoryData.ViewType.GameOver.ordinal -> TypeGameOverViewHolder(
                TypeDrawerGameItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            GameHistoryData.ViewType.GameTurn.ordinal -> TypeGameTurnViewHolder(
                TypeDrawerGameItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            else -> throw IllegalStateException("Invalid ViewType") // 도달 가능성 없음.
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].viewType.ordinal
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TypeGameOverViewHolder -> {
                holder.bind(data = data[position] as GameHistoryData.TypeGameOver)
            }

            is TypeGameTurnViewHolder -> {
                holder.bind(data = data[position] as GameHistoryData.TypeGameTurn)
            }
        }
    }

    inner class TypeGameOverViewHolder(private val binding: TypeDrawerGameItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: GameHistoryData.TypeGameOver) {
            val gameViewData = data.drawerGameViewData
            setTicTacToeButton(binding, gameViewData.playground)
            binding.drawerElement.setBackgroundResource(gameViewData.imageRes)
            binding.drawerResult.text = gameViewData.gameStatus
        }

    }

    inner class TypeGameTurnViewHolder(private val binding: TypeDrawerGameItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: GameHistoryData.TypeGameTurn) {
            val gameViewData = data.drawerGameViewData
            binding.drawerTurn.text = "${gameViewData.turnIndex}턴"
            setTicTacToeButton(binding, gameViewData.playground)
            binding.drawerElement.setBackgroundResource(gameViewData.imageRes)
            binding.drawerResult.setOnClickListener {
                gameViewData.turnIndex?.let(viewModel::go)
            }
        }
    }

    private fun setTicTacToeButton(binding: TypeDrawerGameItemViewBinding, playground: Map<Int, String>) {
        for (i in 0..8) {
            val textView = getTicTacToeTextView(binding, i)
            textView.text = playground[i] ?: ""
        }
    }

    private fun getTicTacToeTextView(binding: TypeDrawerGameItemViewBinding, n: Int): TextView = when (n) {
        0 -> binding.drawerTicTacToe0
        1 -> binding.drawerTicTacToe1
        2 -> binding.drawerTicTacToe2
        3 -> binding.drawerTicTacToe3
        4 -> binding.drawerTicTacToe4
        5 -> binding.drawerTicTacToe5
        6 -> binding.drawerTicTacToe6
        7 -> binding.drawerTicTacToe7
        8 -> binding.drawerTicTacToe8
        else -> throw IllegalStateException("Invalid Tic-Tac-Toe Button Number")
    }


}

sealed class GameHistoryData(val viewType: ViewType) {

    data class TypeGameOver(val drawerGameViewData: DrawerGameViewData) : GameHistoryData(ViewType.GameOver)

    data class TypeGameTurn(val drawerGameViewData: DrawerGameViewData) : GameHistoryData(ViewType.GameTurn)

    enum class ViewType { GameOver, GameTurn }

}