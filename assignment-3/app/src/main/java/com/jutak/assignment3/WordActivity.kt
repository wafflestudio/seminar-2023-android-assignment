package com.jutak.assignment3
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityWordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wordList = intent.getParcelableExtra<MyMultiData.WordInfo>("wordList")!!
        binding.wordListName.text = wordList.name
        binding.recyclerView.apply {
            adapter = WordsAdapter(wordList.word_list, this@WordActivity)
            layoutManager = LinearLayoutManager(this@WordActivity)
        }

        binding.goBackButton.setOnClickListener {
            finish()
        }
    }
}
