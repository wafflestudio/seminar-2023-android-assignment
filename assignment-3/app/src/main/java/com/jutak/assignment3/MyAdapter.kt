package com.jutak.assignment3;

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.VocabularyListBinding


class MyAdapter(private val data: List<Voca>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(VocabularyListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val info = data[position]
        (holder as MyViewHolder).bind(info)
    }

    private inner class MyViewHolder(private val binding: VocabularyListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Voca){
            binding.owner.text = data.owner
            binding.name.text = data.name
            //Log.d("voca_list", data.owner)
        }
    }
}

