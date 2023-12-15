package com.jutak.assignment3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment3.databinding.ItemWordSetBinding


class WordSetAdapter(
    private var wordSets: MutableList<WordSet>,
    private val context: Context,
) : RecyclerView.Adapter<WordSetAdapter.WordSetViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordSetViewHolder {
        val binding = ItemWordSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordSetViewHolder(binding, context)
    }


    override fun onBindViewHolder(holder: WordSetViewHolder, position: Int) {
        holder.bind(wordSets[position])
    }

    override fun getItemCount(): Int = wordSets.size


    fun updateData(newWordSets: List<WordSet>) {
        wordSets.clear()
        wordSets.addAll(newWordSets)
        notifyDataSetChanged()
    }




    class WordSetViewHolder(
        private val binding: ItemWordSetBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(wordSet: WordSet) {
            binding.ownerName.text = wordSet.owner
            binding.setName.text = wordSet.name

            binding.root.setOnClickListener {
                Intent(context, DetailActivity::class.java).apply {
                    putExtra("id", wordSet.id)
                    putExtra("wordSetName", wordSet.name)
                    context.startActivity(this)
                }
            }
        }
    }

    data class WordSet(
        val id: Int,
        val name: String,
        val owner: String
    )
}
