package com.jutak.assignment3

import android.content.Context
import android.content.Intent
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
    private lateinit var adapter: WordsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val wordList = intent.getParcelableExtra<MyMultiData.WordInfo>("wordList")!!
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.wordListName.text = wordList.name
        adapter = WordsAdapter(wordList.word_list, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.goBackButton.setOnClickListener{
            finish()
        }

    }
}