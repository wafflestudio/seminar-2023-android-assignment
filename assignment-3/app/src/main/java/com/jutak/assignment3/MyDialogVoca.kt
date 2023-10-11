package com.jutak.assignment3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.jutak.assignment3.databinding.DialogVocaBinding

class MyDialogVoca : DialogFragment() {
    interface DialogListener {
        fun onInputReceived(owner:String, name:String, pw:String){}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogVocaBinding.inflate(layoutInflater)

        binding.cancel.setOnClickListener{
            dismiss()
        }

        binding.confirm.setOnClickListener{
            val listener = activity as DialogListener
            listener.onInputReceived(binding.nameAnswer.text.toString(), binding.ownerAnswer.text.toString(), binding.pwAnswer.text.toString())
            dismiss()
        }

        return binding.root
    }
}