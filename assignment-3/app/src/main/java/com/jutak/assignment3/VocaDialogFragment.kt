package com.jutak.assignment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.assignment3.databinding.DialogVocaBinding

class VocaDialogFragment : DialogFragment() {
    private var _binding: DialogVocaBinding? = null
    private val binding get() = _binding!!

    interface DialogInputListener {
        fun onInputSubmitted(owner: String, name: String, password: String)
    }

    private val inputListener: DialogInputListener?
        get() = activity as? DialogInputListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogVocaBinding.inflate(inflater, container, false)
        setupClickListeners()
        return binding.root
    }

    private fun setupClickListeners() {
        binding.apply {
            btnCancel.setOnClickListener {
                dismiss()
            }
            btnConfirm.setOnClickListener {
                inputListener?.onInputSubmitted(
                    ownerAnswer.text.toString(),
                    nameAnswer.text.toString(),
                    pwAnswer.text.toString()
                )
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
