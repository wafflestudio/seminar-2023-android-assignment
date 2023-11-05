package com.jutak.assignment3

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.MainWordListsBinding

class MyMultiAdapter(
    private var list:List<WordBook>,
    private var onItemClick: (id : Int) ->Unit
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WordListViewHolder(MainWordListsBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position]
        (holder as WordListViewHolder).bind(data)
    }

    inner class WordListViewHolder(private val binding: MainWordListsBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(data:WordBook){
                binding.host.text = data.owner
                binding.list.text = data.name
                binding.root.setOnClickListener{
                    onItemClick.invoke(data.id)
                }
            }
    }

}