package com.jutak.assignment3

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


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WordListsInfoAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getWordListsInfo() //단어장 목록 GET
        Log.d(viewModel.wordListsInfo.value.toString(),"aaaa")
        adapter = WordListsInfoAdapter(list = viewModel.wordListsInfo.value.orEmpty())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        /*binding.addListButton.setOnClickListener{
            //Dialog 써야 함
        }*/

        viewModel.wordListsInfo.observe(this){
            //adapter.notifyItemChanged(viewModel.wordListsInfo.value!!.lastIndex)
            adapter.notifyDataSetChanged()
            Log.d("iun","aaaa")
        }
    }
}