package com.jutak.assignment3

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.jutak.assignment3.databinding.AddWordDialogBinding
import com.jutak.assignment3.databinding.WordListDialogBinding

class AddWordDialog() : DialogFragment(){
    private lateinit var binding: AddWordDialogBinding
    interface AddWordDialogListener {
        fun addWord(newWord : Word)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AddWordDialogBinding.inflate(layoutInflater)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
                .setPositiveButton("확인", DialogInterface.OnClickListener {
                        dialog,_ ->
                    val spell = binding.spell.text.toString()
                    val meaning = binding.meaning.text.toString()
                    val synonym = binding.synonym.text.toString()
                    val antonym = binding.antonym.text.toString()
                    val sentence = binding.sentence.text.toString()

                    val newWord = Word(spell,meaning, synonym, antonym, sentence)
                    val dialogListener = requireActivity() as AddWordDialog.AddWordDialogListener
                    dialogListener.addWord(newWord)

                    dialog.dismiss()
                }).setNegativeButton("취소", DialogInterface.OnClickListener {
                        dialog, _ -> dialog.dismiss()
                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }



}