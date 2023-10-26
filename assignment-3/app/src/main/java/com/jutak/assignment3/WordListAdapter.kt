package com.jutak.assignment3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment3.databinding.ItemVocabularyBinding

class WordListAdapter(private var vocabularyList: List<MyMultiData.Voca>) :
    RecyclerView.Adapter<WordListAdapter.VocabularyViewHolder>() {

    private var onItemClickListener: ((View, Int) -> Unit)? = null

    fun setItemClickListener(listener: ((View, Int) -> Unit)?){
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabularyViewHolder {
        val itemBinding = ItemVocabularyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VocabularyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: VocabularyViewHolder, position: Int) {
        val vocabulary = vocabularyList[position]
        holder.bind(vocabulary)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(it, position)
        }
    }

    override fun getItemCount(): Int = vocabularyList.size
    fun updateVocaList(newList: List<MyMultiData.Voca>) {
        vocabularyList = newList.toMutableList()
        notifyDataSetChanged()
    }

    inner class VocabularyViewHolder(private val binding: ItemVocabularyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(vocaData: MyMultiData.Voca) {
            binding.ownerTextView.text = vocaData.owner
            binding.nameTextView.text = vocaData.name
        }
    }
}
