package com.jutak.assignment3

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.jutak.assignment3.databinding.WordListDialogBinding

class WordListDialog() : DialogFragment(){
    private lateinit var binding: WordListDialogBinding

    interface WordListDialogListener {
        fun createWordList(newWordList : WordListWrite)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = WordListDialogBinding.inflate(layoutInflater)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(binding.root)
                .setPositiveButton("확인", DialogInterface.OnClickListener {
                        dialog,_ ->
                    val owner = binding.dialogOwnerEditText.text.toString()
                    val name = binding.dialogNameEditText.text.toString()
                    val password = binding.dialogPasswordEditText.text.toString()

                    val newWordList = WordListWrite(owner, name, password)
                    val dialogListener = requireActivity() as WordListDialog.WordListDialogListener
                    dialogListener.createWordList(newWordList)

                    dialog.dismiss()
                }).setNegativeButton("취소", DialogInterface.OnClickListener {
                        dialog, _ -> dialog.dismiss()
                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }



}