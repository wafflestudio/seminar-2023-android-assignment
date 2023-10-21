package com.jutak.assignment3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.ItemWordSetListBinding

class WordSetListAdapter(
    private val list: List<WordSet>,
    private val context: Context,
): RecyclerView.Adapter<WordSetListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemWordSetListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class MyViewHolder(private val binding: ItemWordSetListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(wordSet: WordSet) {
            binding.ownerName.text = wordSet.owner
            binding.setName.text = wordSet.name

            binding.root.setOnClickListener {
                Intent(context, DetailActivity::class.java).apply {
                    putExtra("id", wordSet.id)
                    putExtra("wordSetName", wordSet.name)
                }.run { context.startActivity(this) }
            }

        }
    }

}