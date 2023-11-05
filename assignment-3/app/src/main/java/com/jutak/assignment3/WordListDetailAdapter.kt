package com.jutak.assignment3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.WordItemViewBinding
import com.jutak.assignment3.model.Word

class WordListDetailAdapter(
    private val list: LiveData<List<Word>>,
    private val onClickItem: (word: Word) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WordViewHolder(
            WordItemViewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int = list.value!!.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list.value!![position]
        when (holder) {
            is WordViewHolder -> holder.bind(data)
            else -> {}
        }
    }

    private inner class WordViewHolder(private val binding: WordItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            binding.spell.text = word.spell
            binding.meaning.text = word.meaning
            binding.root.setOnClickListener {
                onClickItem(word)
            }
        }
    }

}