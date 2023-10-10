package com.jutak.assignment3;

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
    }

    private inner class MyViewHolder(binding: VocabularyListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }
    }
}

