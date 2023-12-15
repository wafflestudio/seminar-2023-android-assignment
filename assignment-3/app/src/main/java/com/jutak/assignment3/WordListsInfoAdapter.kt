package com.jutak.assignment3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.WordsListInfoViewBinding

class WordListsInfoAdapter(
    private var list: List<MyMultiData.WordListsInfo>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<WordListsInfoAdapter.WordListsInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListsInfoViewHolder {
        val binding = WordsListInfoViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordListsInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordListsInfoViewHolder, position: Int) {
        val wordListInfo = list[position]
        holder.bind(wordListInfo)
    }

    override fun getItemCount(): Int = list.size

    fun submitList(newList: List<MyMultiData.WordListsInfo>) {
        val startIndex = list.size
        list = newList
        notifyItemRangeInserted(startIndex, newList.size - startIndex)
    }

    inner class WordListsInfoViewHolder(private val binding: WordsListInfoViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(wordListsInfo: MyMultiData.WordListsInfo) {
            with(binding) {
                owner.text = wordListsInfo.owner
                listName.text = wordListsInfo.name
                root.setOnClickListener { onClick(wordListsInfo.id) }
            }
        }
    }
}
