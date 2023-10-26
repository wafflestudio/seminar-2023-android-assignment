package com.jutak.assignment3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.DialogWordInfoBinding
import com.jutak.assignment3.databinding.WordBinding
import com.jutak.assignment3.databinding.WordListBinding

class WordAdapter (
    private val list: LiveData<List<Word>>,
    private val context: Context
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = list.value!!.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val binding = WordBinding.inflate(LayoutInflater.from(parent.context))
        val binding2 = DialogWordInfoBinding.inflate(LayoutInflater.from(parent.context))
        return WordViewHolder(binding, binding2)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        val data = list.value!![position]
        if (holder is WordAdapter.WordViewHolder){
            holder.bind(data)
        }
    }

    private inner class WordViewHolder(
        private val binding: WordBinding,
        private val binding2: DialogWordInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            binding.spell.text = word.spell
            binding.meaning.text = word.meaning
            binding.data.setOnClickListener {
                val builder = AlertDialog.Builder(context).apply {
                    setView(binding2.root)
                }

                val dialog = builder.create().apply {
                    show()
                }

                binding2.eSpell.text = word.spell
                binding2.eMeaning.text = word.meaning
                binding2.eSynonym.text = word.synonym
                binding2.eAntonym.text = word.antonym
                binding2.eSentence.text = word.sentence

                binding2.close.setOnClickListener{
                    dialog.dismiss()
                }
            }
        }
    }

    }