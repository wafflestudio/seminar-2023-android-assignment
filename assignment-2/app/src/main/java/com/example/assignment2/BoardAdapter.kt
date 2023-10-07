package com.example.assignment2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.assignment2.databinding.BoardItemBinding

class BoardAdapter(private val context: Context, private val viewModel: MainViewModel) :BaseAdapter(){

    private val board : List<Mark>
        get() = viewModel.board.value!!

    override fun getCount(): Int = board.size
    override fun getItemId(position: Int): Long = 0
    override fun getItem(position: Int): Mark = board[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mark = getItem(position)
        val binding = BoardItemBinding.inflate(LayoutInflater.from(context))

        binding.boardItem.text = when(mark){
            Mark.EMPTY -> ""
            Mark.PLAYER1 -> "O"
            Mark.PLAYER2 -> "X"
        }
        binding.boardItem.setOnClickListener() {
            if (binding.boardItem.text == "") {
                viewModel.boardItemClickEvent(position)
                binding.boardItem.text = when(mark){
                    Mark.EMPTY -> ""
                    Mark.PLAYER1 -> "O"
                    Mark.PLAYER2 -> "X"
                }
            }
        }




        return binding.root
    }
}
