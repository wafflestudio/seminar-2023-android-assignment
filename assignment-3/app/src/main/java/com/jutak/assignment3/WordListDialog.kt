package com.jutak.assignment3

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.jutak.assignment3.databinding.WordListDialogBinding

class WordListDialog(private val viewModel: MainViewModel) : DialogFragment(){
    private lateinit var binding: WordListDialogBinding

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
                    val wordListOwner = binding.dialogOwnerEditText.text.toString()
                    val wordListName = binding.dialogNameEditText.text.toString()
                    val wordListPassword = binding.dialogPasswordEditText.text.toString()
                    viewModel.createWordList(wordListOwner,wordListName,wordListPassword)
                    dialog.dismiss()
                }).setNegativeButton("취소", DialogInterface.OnClickListener {
                        dialog, _ -> dialog.dismiss()
                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }



}