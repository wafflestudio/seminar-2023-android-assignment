package com.jutak.assignment3.views.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.data.model.SimpleWordList
import com.jutak.assignment3.databinding.WordListLayoutBinding

class WordListsAdapter(
    private val onItemClick: (id: Int) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<SimpleWordList> = listOf()

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(WordListLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(list[position])
    }

    inner class ViewHolder(private val binding: WordListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(wordList: SimpleWordList) {
            binding.ownerText.text = wordList.owner
            binding.name.text = wordList.name
            binding.root.setOnClickListener {
                onItemClick.invoke(wordList.id)
            }
        }
    }

    fun submitList(list: List<SimpleWordList>) {
        this.list = list
        notifyDataSetChanged()
    }
}