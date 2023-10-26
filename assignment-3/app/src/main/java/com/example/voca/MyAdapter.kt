package com.example.voca

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.voca.databinding.VocasBinding

class MyAdapter(private val data: LiveData<List<MyDataTypes.VocaListInfo>>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VocaViewHolder(VocasBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is VocaViewHolder->{
                holder.set(position)
            }
        }
    }
    inner class VocaViewHolder(private val binding:VocasBinding): RecyclerView.ViewHolder(binding.root){

        /*
        val count=binding.historyTitle
        val boards=binding.historyBoard
        val restartButton=binding.historyGo
        */

        fun set(position: Int){
            binding.ownerName.text=data.value!![position].owner
            binding.vocalListName.text=data.value!![position].name
            /*
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
            }*/
        }
    }

}