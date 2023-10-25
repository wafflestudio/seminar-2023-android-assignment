package com.jutak.assignment3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.WordListItemBinding

class WordListAdapter(private val onWordListClick : (id : Int)-> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data  : List<WordListRead> = emptyList()
    fun submitList(newData : List<WordListRead>){
        data = newData
    }

    inner class WordListItemViewHolder(private val binding: WordListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WordListRead) {
            binding.owner.text = item.owner
            binding.name.text = item.name
            binding.wordListItem.setOnClickListener { onWordListClick(item.id) }

        }
    }
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WordListItemBinding.inflate(inflater, parent, false)
        return WordListItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        (holder as WordListItemViewHolder).bind(item)
    }
}