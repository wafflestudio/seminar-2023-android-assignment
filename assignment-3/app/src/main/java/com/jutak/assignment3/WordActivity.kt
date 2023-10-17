package com.jutak.assignment3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityWordBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



class WordActivity(): AppCompatActivity() {

    private lateinit var binding: ActivityWordBinding
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = WordAdapter()
        binding.recyclerViewWords.adapter = adapter
        binding.recyclerViewWords.layoutManager = LinearLayoutManager(this)

        viewModel.words.observe(this, Observer {
            adapter.submitList(it.words)
            adapter.notifyDataSetChanged()
        })

    }
}