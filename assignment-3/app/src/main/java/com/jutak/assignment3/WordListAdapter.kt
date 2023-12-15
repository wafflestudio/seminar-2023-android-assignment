package com.jutak.assignment3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.assignment3.R
import com.assignment3.databinding.DetailDialogBinding
import com.assignment3.databinding.ItemWordListBinding

class WordListAdapter(
    private val words: List<Word>,
    private val onWordClicked: (Int) -> Unit
) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemBinding = ItemWordListBinding.bind(itemView)
        private var alertDialog: AlertDialog? = null

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onWordClicked(adapterPosition)
                }
            }
        }

        fun bindData(word: Word) {
            with(itemBinding) {
                tvSpell.text = word.spell
                tvMeaning.text = word.meaning
            }
        }

        private fun showDialog(word: Word) {
            val dialogBinding = DetailDialogBinding.inflate(LayoutInflater.from(itemView.context))

            with(dialogBinding) {
                tvWord.text = word.spell
                tvMeaning.text = word.meaning
                tvSynonym.text = word.synonym
                tvAntonym.text = word.antonym

                btnClose.setOnClickListener {
                    alertDialog?.dismiss()
                }
            }

            val dialogBuilder = AlertDialog.Builder(itemView.context)
            alertDialog = dialogBuilder.create().apply {
                setView(dialogBinding.root)
                show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word_list, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bindData(words[position])
    }

    override fun getItemCount(): Int = words.size
}

data class Word(
    val word: String,
    val spell: String,
    val meaning: String,
    val synonym: String,
    val antonym: String,
    val sentence: String
)
