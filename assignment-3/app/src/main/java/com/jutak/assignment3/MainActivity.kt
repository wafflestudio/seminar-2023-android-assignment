package com.jutak.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val adapter: WordListsInfoAdapter by lazy {
        WordListsInfoAdapter(emptyList()) { id ->
            onClick(id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupRecyclerView()
        setupViewModelObservers()
        setupAddListButton()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupViewModelObservers() {
        viewModel.getWordListsInfo()
        viewModel.wordListsLiveData.observe(this) { wordLists ->
            wordLists?.let {
                adapter.submitList(it)
            }
        }
    }

    private fun setupAddListButton() {
        binding.addListButton.setOnClickListener {
            viewModel.showAlertDialog(this)
        }
    }

    private fun onClick(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val words = viewModel.getWords(id)
            withContext(Dispatchers.Main) {
                startWordActivity(words)
            }
        }
    }

    private fun startWordActivity(words: MyMultiData.WordInfo) {
        val intent = Intent(this@MainActivity, WordActivity::class.java)
        intent.putExtra("wordList", words)
        startActivity(intent)
    }
}
