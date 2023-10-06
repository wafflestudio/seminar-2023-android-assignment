package com.example.assignment2

import android.content.Context
import android.util.Log
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
        val mark = getItem(position)
        val binding = BoardItemBinding.inflate(LayoutInflater.from(context))
        //아래 when 절에서, 기준 변수를 mark로 했더니, init이 호출될때, 이전에 가지고 있었던 mark를 토대로 getView를 다시 실행하여,
        //viewModel의 board 상태는 초기화되었으나, UI는 초기화되지 않는 오류가 나서, 아래와 같이 사용했습니다.
        binding.boardItem.text = when(viewModel.board.value!![position]){
            Mark.EMPTY -> "empty"
            Mark.PLAYER1 -> "O"
            Mark.PLAYER2 -> "X"
        }
        binding.boardItem.setOnClickListener() {
            if (binding.boardItem.text == "empty") {
                viewModel.boardItemClickEvent(position)
                binding.boardItem.text = when(viewModel.board.value!![position]){
                    Mark.EMPTY -> "empty"
                    Mark.PLAYER1 -> "O"
                    Mark.PLAYER2 -> "X"
                }
            }
        }




        return binding.root
    }
}
