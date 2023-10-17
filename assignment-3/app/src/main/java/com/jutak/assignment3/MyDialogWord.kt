package com.jutak.assignment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.jutak.assignment3.databinding.DialogWordBinding

class MyDialogWord : DialogFragment() {
    var viewModel: MyViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogWordBinding.inflate(layoutInflater)

        //내용 채우기
        val word = viewModel!!.word!!
        binding.spellingFill.text = word.spell
        binding.meaningFill.text = word.meaning
        binding.synonymFill.text = word.synonym
        binding.antonymFill.text = word.antonym
        binding.sentenceFill.text = word.sentence

        binding.close.setOnClickListener{
            dismiss()
        }

        return binding.root
    }
}