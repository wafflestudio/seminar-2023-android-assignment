package com.jutak.assignment3

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
    private lateinit var viewModel: MainViewModel
    @Inject
    lateinit var api: MyRestAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = SharedMainViewModelFactory(api)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]


        val adapter = WordAdapter()
        binding.recyclerViewWords.adapter = adapter
        binding.recyclerViewWords.layoutManager = LinearLayoutManager(this)

        Log.d("aaa",viewModel.words.value.toString())

        viewModel.words.observe(this, Observer {
            adapter.submitList(it.words)
            adapter.notifyDataSetChanged()
        })

    }
}