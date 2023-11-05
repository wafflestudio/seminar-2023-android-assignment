package com.jutak.assignment3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jutak.assignment3.databinding.DetailWordListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailVocaActivity: AppCompatActivity() {

    private lateinit var binding: DetailWordListBinding
    private val viewModel:DetailVocaViewModel by viewModels();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DetailWordListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var receivedData = intent.getParcelableExtra<WordList>("word_list")

        val adapter = receivedData?.let {
            DetailVocaMultiAdapter(it.wordList, onItemClick = {
                word ->
            })
        }
        binding.recyclerView.adapter
    }
}