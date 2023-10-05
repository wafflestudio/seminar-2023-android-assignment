package com.example.assignment2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.assignment2.databinding.BoardItemBinding

class BoardAdapter(private val context: Context, private val viewModel: MainViewModel) :BaseAdapter(){

    private val board : List<Mark> = viewModel.board.value!!

    override fun getCount(): Int = board.size
    override fun getItemId(position: Int): Long = 0
    override fun getItem(position: Int): Mark = board[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // position 위치의 데이터를 어떻게 View로 그릴래?
        val mark = getItem(position)
        val binding = BoardItemBinding.inflate(LayoutInflater.from(context))
        binding.boardItem.text = when(mark){
            Mark.EMPTY -> "empty"
            Mark.PLAYER1 -> "O"
            Mark.PLAYER2 -> "X"
        }
        binding.boardItem.setOnClickListener() {
            if (binding.boardItem.text == "empty") {
                viewModel.boardItemClickEvent(position)
                binding.boardItem.text = when(getItem(position)){
                    Mark.EMPTY -> "empty"
                    Mark.PLAYER1 -> "O"
                    Mark.PLAYER2 -> "X"
                }
            }


        }



        return binding.root
    }
}
