package com.jutak.assignment3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.WordListItemViewBinding
import com.jutak.assignment3.model.WordListBrief

class WordListsAdapter(
    private val list: LiveData<List<WordListBrief>>,
    private val onClickItem: (id: Int) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WordListViewHolder(
            WordListItemViewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int = list.value!!.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list.value!![position]
        when (holder) {
            is WordListViewHolder -> holder.bind(data)
            else -> {}
        }
    }

    private inner class WordListViewHolder(private val binding: WordListItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(wordList: WordListBrief) {
            binding.owner.text = wordList.owner
            binding.name.text = wordList.name
            binding.root.setOnClickListener {
                onClickItem(wordList.id)
            }
        }
    }

}