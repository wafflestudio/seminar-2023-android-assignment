package com.jutak.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityWordBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class WordActivity(): AppCompatActivity() {

    private lateinit var binding: ActivityWordBinding
    private val viewModel : WordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wordListId = intent.getIntExtra("word_list_id",0)

        val adapter = WordAdapter(::onWordClick)
        binding.recyclerViewWords.adapter = adapter
        binding.recyclerViewWords.layoutManager = LinearLayoutManager(this)

        viewModel.fetchWords(wordListId)

        viewModel.words.observe(this, Observer {
            binding.wordListTitle.text = it.name
            adapter.submitList(it.words)
            adapter.notifyDataSetChanged()
        })

        binding.backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            setResult(RESULT_OK,intent)
            finish()
        }


    }

    private fun onWordClick(word : Word){
        val wordDialog = WordDialog(word)
        wordDialog.show(supportFragmentManager, "wordDialogShow")
    }
}