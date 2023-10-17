package com.jutak.assignment3

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.jutak.assignment3.databinding.EditListDialogBinding
import com.jutak.assignment3.databinding.WordDialogBinding
import com.jutak.assignment3.databinding.WordListDialogBinding

class DeleteListDialog() : DialogFragment(){

    interface DeleteListDialogListener {
        fun deleteDialog()
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("단어장 삭제하기")
                .setMessage("단어장을 정말 삭제하겠습니까?")
                .setPositiveButton("확인", DialogInterface.OnClickListener {
                        dialog,_ ->
                    val dialogListener = requireActivity() as DeleteListDialog.DeleteListDialogListener
                    dialogListener.deleteDialog()
                    dialog.dismiss()
                }).setNegativeButton("취소", DialogInterface.OnClickListener {
                        dialog, _ -> dialog.dismiss()
                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}