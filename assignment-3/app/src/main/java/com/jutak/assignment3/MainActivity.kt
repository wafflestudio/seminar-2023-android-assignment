package com.jutak.assignment3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordListAdapter(viewModel.wordLists)
        binding.wordListRecyclerView.adapter = adapter
        binding.wordListRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.wordLists.observe(this) {
            binding.wordListRecyclerView.adapter?.notifyDataSetChanged()
        }

    }

}