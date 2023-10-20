package com.jutak.assignment3

import android.content.Context
import android.service.autofill.OnClickAction
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.AddDialogLayoutBinding
import com.jutak.assignment3.databinding.DetailLayoutBinding
import com.jutak.assignment3.databinding.WordLayoutBinding

class WordsAdapter(
    private val data: List<MyMultiData.Word>,
    private val onClickDialog: (Unit) -> Unit,
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WordsViewHolder(WordLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is WordsAdapter.WordsViewHolder)
            holder.bind(data[position])
    }

    inner class WordsViewHolder(private val binding: WordLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(words: MyMultiData.Word){
                binding.spell.text = words.spell
                binding.meaning.text = words.meaning
                binding.word.setOnClickListener{
                    Log.d("aaaa",words.spell.toString())
                    onClickDialog
                }
            }
        }

}