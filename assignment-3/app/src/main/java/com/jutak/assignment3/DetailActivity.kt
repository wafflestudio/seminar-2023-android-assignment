package com.jutak.assignment3

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityDetailBinding

class DetailActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: MainViewModel by viewModels()
    private var id = 0
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id", 0)
        name = intent.getStringExtra("name").toString()

        binding.title.text = name

        val adapter = WordAdapter(viewModel.wordList, this)
        binding.wordRecyclerView.adapter = adapter
        binding.wordRecyclerView.layoutManager =  LinearLayoutManager(this)

        viewModel.fetchListDetail(id)
        viewModel.wordList.observe(this){
            adapter.notifyDataSetChanged()
        }

        binding.btn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}