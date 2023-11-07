package com.jutak.assignment3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.WordListsInfoViewBinding
class WordListsInfoAdapter(
    private val onClick: (Int) -> Unit,
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<MyMultiData.WordListsInfo> = listOf()
    fun submitList(newList: List<MyMultiData.WordListsInfo>){
        list = newList
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WordListsInfoViewHolder(WordListsInfoViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is WordListsInfoViewHolder)
            holder.bind(position, list[position])
    }

    inner class WordListsInfoViewHolder(private val binding: WordListsInfoViewBinding):
    RecyclerView.ViewHolder(binding.root)
    {
        fun bind(position: Int, wordListsInfo: MyMultiData.WordListsInfo){
            binding.owner.text = wordListsInfo.owner
            binding.listName.text = wordListsInfo.name
            binding.wordListInfo.setOnClickListener{
                onClick(wordListsInfo.id)
            }
        }
    }
}