package com.jutak.assignment3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.jutak.assignment3.databinding.DialogWordDetailBinding
import com.jutak.assignment3.databinding.ItemWordListBinding

class WordListAdapter(
    private val list: List<Word>,
    private val context: Context,
): RecyclerView.Adapter<WordListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemWordListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val dialogBinding = DialogWordDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, dialogBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class MyViewHolder(
        private val binding: ItemWordListBinding,
        private val dialogBinding: DialogWordDetailBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            binding.spell.text = word.spell
            binding.meaning.text = word.meaning

            binding.root.setOnClickListener {

                val builder = AlertDialog.Builder(context).apply {
                    setView(dialogBinding.root)
                }
                val dialog = builder.create().apply {
                    show()
                }

                dialogBinding.word.text = word.spell
                dialogBinding.meaning.text = word.meaning
                dialogBinding.synonym.text = word.synonym
                dialogBinding.antonym.text = word.antonym
                dialogBinding.sentence.text = word.sentence

                dialogBinding.closeButton.setOnClickListener {
                    dialog.dismiss()
                }

            }

        }
    }

}