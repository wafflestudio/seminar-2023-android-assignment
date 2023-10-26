package com.jutak.assignment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.assignment3.databinding.DialogWordBinding

class WordDetailDialog : DialogFragment() {
    var viewModel: MainViewModel? = null

    private var _binding: DialogWordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogWordBinding.inflate(inflater, container, false)
        viewModel?.word?.observe(viewLifecycleOwner) { word ->
            with(binding) {
                spellingFill.text = word.spell
                meaningFill.text = word.meaning
                synonymFill.text = word.synonym ?: "N/A"
                antonymFill.text = word.antonym ?: "N/A"
                exampleFill.text = word.example ?: "N/A"
            }
        }


        binding.closeButton.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
