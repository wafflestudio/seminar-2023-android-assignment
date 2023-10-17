package com.jutak.assignment3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.WordItemBinding

class WordAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data  : List<Word> = emptyList()
    fun submitList(newData : List<Word>){
        data = newData
    }

    inner class WordItemViewHolder(private val binding: WordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Word) {
            binding.english.text = item.spell
            binding.korean.text = item.meaning
            // binding.wordListItem.setOnClickListener { onWordListClick(item.id) }

        }
    }
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WordItemBinding.inflate(inflater, parent, false)
        return WordItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        (holder as WordItemViewHolder).bind(item)
    }
}
