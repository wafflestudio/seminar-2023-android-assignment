package com.jutak.assignment3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.WordListsInfoViewBinding
class WordListsInfoAdapter(
    private val list: List<WordListsInfo>
): RecyclerView.Adapter<RecyclerView.ViewHolder>(

) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WordListsInfoViewHolder(WordListsInfoViewBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is WordListsInfoViewHolder)
            holder.bind(list[position])
    }

    inner class WordListsInfoViewHolder(private val binding: WordListsInfoViewBinding):
    RecyclerView.ViewHolder(binding.root)
    {
        fun bind(wordListsInfo: WordListsInfo){
            binding.wordListInfo.text = wordListsInfo.owner + "\t" + wordListsInfo.name

        }
    }
}