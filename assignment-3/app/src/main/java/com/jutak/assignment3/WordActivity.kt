package com.jutak.assignment3

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityWordBinding
import com.jutak.assignment3.databinding.DetailLayoutBinding
import com.jutak.assignment3.databinding.WordLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class WordActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWordBinding
    private val viewModel by viewModels<WordViewModel>()
    private lateinit var adapter: WordsAdapter

    private fun onClickDialog(){
        Log.d("aaaa","Aaaa")
            /*val alertDialog = AlertDialog.Builder(this)
            val binding = DetailLayoutBinding.inflate(LayoutInflater.from(this))
            alertDialog.setView(binding.root)
            alertDialog.show()
            //binding.closeButton.setOnClickListener()*/
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val wordList = intent.getParcelableExtra<MyMultiData.WordInfo>("wordList")!!
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.wordListName.text = wordList.name
        adapter = WordsAdapter(wordList.word_list) { onClickDialog() }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }
}