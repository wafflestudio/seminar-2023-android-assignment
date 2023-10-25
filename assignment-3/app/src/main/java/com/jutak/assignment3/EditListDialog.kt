package com.jutak.assignment3

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.jutak.assignment3.databinding.EditListDialogBinding
import com.jutak.assignment3.databinding.WordDialogBinding
import com.jutak.assignment3.databinding.WordListDialogBinding

class EditListDialog() : DialogFragment(){

    private lateinit var binding: EditListDialogBinding

    interface EditListDialogListener {
        fun onDialogPasswordReceived(password: String)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = EditListDialogBinding.inflate(layoutInflater)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
                .setPositiveButton("확인", DialogInterface.OnClickListener {
                        dialog,_ ->
                    val password = binding.password.text.toString()

                    val dialogListener = requireActivity() as EditListDialogListener
                    dialogListener.onDialogPasswordReceived(password)

                    dialog.dismiss()
                }).setNegativeButton("취소", DialogInterface.OnClickListener {
                        dialog, _ -> dialog.dismiss()
                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}