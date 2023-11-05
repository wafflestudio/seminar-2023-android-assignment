package com.jutak.assignment3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.DetailWordBinding
import com.jutak.assignment3.databinding.MainWordListsBinding

class DetailVocaMultiAdapter(
    private var list:List<Word>,
    private var onItemClick: (word: Word) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WordViewHolder(DetailWordBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position]
        (holder as WordViewHolder).bind(data)
    }

    inner class WordViewHolder(private val binding: DetailWordBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(data:Word){
            binding.spell.text = data.spell
            binding.mean.text = data.meaning
            binding.root.setOnClickListener{
                onItemClick.invoke(data)
            }
        }
    }
}