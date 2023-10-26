package com.jutak.assignment3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.WordListBinding

class WordListAdapter(
    private val list: LiveData<List<ExList>>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = list.value!!.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val binding = WordListBinding.inflate(LayoutInflater.from(parent.context))
        return WordListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        val data = list.value!![position]
        if (holder is WordListViewHolder){
            if (data != null) {
                holder.bind(data)
            }
        }
    }

    private inner class WordListViewHolder(private val binding: WordListBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(wordList: ExList) {
            binding.name.text = wordList.name
            binding.owner.text = wordList.owner
            binding.data.setOnClickListener {
                val intent: Intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("id", wordList.id)
                intent.putExtra("name", wordList.name)
                context.startActivity(intent)
        }
        }
    }
}