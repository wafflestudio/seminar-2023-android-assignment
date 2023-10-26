package com.jutak.assignment3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment3.databinding.ActivityDetailBinding
//import com.jutak.assignment3.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class DetailActivity : AppCompatActivity(), VocaDialogFragment.DialogInputListener {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        handleIntents()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        binding.wordsRecyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@DetailActivity)
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            fetchVocabularyInfo(intent.getStringExtra("vocaId") ?: "")

            vocabularyInfo.observe(this@DetailActivity) {
                binding.titleTextView.text = it?.name ?: ""
            }

            wordsList.observe(this@DetailActivity) {
                (binding.wordsRecyclerView.adapter as WordListAdapter).notifyDataSetChanged()
            }
        }
    }

    private fun handleIntents() {
        intent.getStringExtra("vocaId")?.let { vocaId ->
            viewModel.fetchVocabularyInfo(vocaId)
        }
    }

    private fun setupClickListeners() {
        (binding.wordsRecyclerView.adapter as WordListAdapter).setItemClickListener{ view, position ->
            viewModel.fetchWordDetails(position)
           // VocaDialogFragment(viewModel).show(supportFragmentManager, "WordDialogFragment")
        }

    }

}
