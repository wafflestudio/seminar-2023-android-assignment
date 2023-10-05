package com.example.tictac

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import com.example.tictac.databinding.HistoryBinding

class MyMultiAdapter(private val data: MutableList<MyData>, private val viewModel: MainViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GameViewHolder(HistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is GameViewHolder->{
                holder.set(position)
            }
        }
    }
    inner class GameViewHolder(private val binding:HistoryBinding): RecyclerView.ViewHolder(binding.root){

        /*
        val count=binding.historyTitle
        val boards=binding.historyBoard
        val restartButton=binding.historyGo
        */

        fun set(position: Int){

            binding.historyTitle.text="${data[position].count}턴"
            val currentBoard=Array(9) { 0 }

            val ii=0
            binding.historyBoard.forEach {
                val textView=it as TextView
                val buttonNum=viewModel.getButtonNum2(textView,binding)

                textView.text=when (data[position].board[buttonNum]){
                    1->"O"
                    2->"X"
                    else->""
                }
                /*
                if(buttonNum==data[position].count-1){
                    textView.text=when (data[position].board[buttonNum]){
                        1->"O"
                        2->"X"
                        else->""
                    }
                }*/
            }
        }
    }
    fun set(binding: HistoryBinding){
        binding.historyTitle.text="?"
    }
}