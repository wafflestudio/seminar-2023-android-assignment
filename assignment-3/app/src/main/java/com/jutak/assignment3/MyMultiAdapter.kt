package com.jutak.assignment3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.MainWordListsBinding

class MyMultiAdapter(
    private var list:List<WordList>
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WordListViewHolder(MainWordListsBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position]
        when(holder){
            is WordListViewHolder -> {
                holder.bind(data)
            }
        }
    }

    inner class WordListViewHolder(private val binding: MainWordListsBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(data:WordList){
                binding.host.text = data.owner
                binding.list.text = data.name
            }
    }

}