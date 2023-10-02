package com.jutak.assignment3.views.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.data.model.Word
import com.jutak.assignment3.databinding.WordLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WordsAdapter(
    private val onItemClick: (idx: Int, word: Word) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<Word> = listOf()

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(WordLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(list[position], position)
    }

    inner class ViewHolder(private val binding: WordLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word, position: Int) {
            binding.spell.text = word.spell
            binding.meaning.text = word.meaning
            binding.root.setOnClickListener {
                onItemClick.invoke(position, word)
            }
        }
    }

    fun submitList(list: List<Word>) {
        this.list = list
        notifyDataSetChanged()
    }
}