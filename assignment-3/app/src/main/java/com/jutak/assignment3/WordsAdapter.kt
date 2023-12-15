package com.jutak.assignment3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.DetailLayoutBinding
import com.jutak.assignment3.databinding.WordLayoutBinding

class WordsAdapter(
    private val data: List<MyMultiData.Word>,
    private val context: Context
): RecyclerView.Adapter<WordsAdapter.WordsViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val binding = WordLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        val word = data[position]
        holder.bind(word)
    }

    inner class WordsViewHolder(private val binding: WordLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(words: MyMultiData.Word) {
            binding.apply {
                spell.text = words.spell
                meaning.text = words.meaning
                word.setOnClickListener {
                    showDialog(words)
                }
            }
        }
    }

    private fun showDialog(word: MyMultiData.Word) {
        AlertDialog.Builder(context).apply {
            val detailBinding = DetailLayoutBinding.inflate(LayoutInflater.from(context))
            setView(detailBinding.root)
            setNegativeButton("닫기") { dialog, _ -> dialog.dismiss() }

            detailBinding.apply {
                spell.text = word.spell
                meaning.text = word.meaning
                synonym.text = word.synonym
                antonym.text = word.antonym
                sentence.text = word.sentence
            }

            show()
        }
    }
}
