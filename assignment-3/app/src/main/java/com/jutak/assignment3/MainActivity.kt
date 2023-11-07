package com.jutak.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WordListsInfoAdapter
    private val viewModel: MainViewModel by viewModels()
    private fun onClick(id: Int) {
        val intent = Intent(this, WordActivity::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            intent.putExtra("wordList", viewModel.getWords(id))
            withContext(Dispatchers.Main) {
                startActivity(intent)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getWordListsInfo() //단어장 목록 GET

        binding.addListButton.setOnClickListener{
            viewModel.showAlertDialog(this)
        }
        adapter = WordListsInfoAdapter {
            onClick(it)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.wordListsLiveData.observe(this){
            it?.let{
                adapter.submitList(it)
            }
            adapter.notifyDataSetChanged()
        }

    }
}