package com.jutak.assignment3

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.jutak.assignment3.databinding.WordDialogBinding
import com.jutak.assignment3.databinding.WordListDialogBinding

class WordDialog(private val word : Word) : DialogFragment(){
    private lateinit var binding: WordDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = WordDialogBinding.inflate(layoutInflater)
        binding.spell.text = word.spell
        binding.meaning.text = word.meaning
        binding.synonym.text = word.synonym
        binding.antonym.text = word.antonym
        binding.sentence.text = word.sentence

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(binding.root)
                .setNegativeButton("취소", DialogInterface.OnClickListener {
                        dialog, _ -> dialog.dismiss()
                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }



}