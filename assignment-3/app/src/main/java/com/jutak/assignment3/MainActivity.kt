package com.jutak.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    @Inject
    lateinit var api: MyRestAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = SharedMainViewModelFactory(api)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]


        val adapter = WordListAdapter(::onWordListClick)
        binding.recyclerViewWordList.adapter = adapter
        binding.recyclerViewWordList.layoutManager = LinearLayoutManager(this)


        viewModel.fetchWordLists()

        viewModel.wordLists.observe(this, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
        val wordListDialog = WordListDialog(viewModel)
        binding.wordListCreateBtn.setOnClickListener {
            wordListDialog.show(supportFragmentManager, "wordListDialogShow")
        }
    }

    private fun onWordListClick(id : Int){
        viewModel.fetchWords(id)
        val intent = Intent(this, WordActivity::class.java)
        startActivity(intent)
    }
}