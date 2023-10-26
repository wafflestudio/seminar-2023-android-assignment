package com.jutak.assignment3


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment3.databinding.ActivityMainBinding
import com.jutak.assignment3.DetailActivity
import com.jutak.assignment3.MainViewModel
import com.jutak.assignment3.VocaDialogFragment
import com.jutak.assignment3.WordListAdapter
//import com.jutak.assignment3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
abstract class MainActivity : AppCompatActivity(), VocaDialogFragment.DialogInputListener {
    private lateinit var mainBinding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val vocaAdapter = WordListAdapter(mutableListOf())
        mainBinding.recyclerView.apply {
            adapter = vocaAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        mainViewModel.fetchVocabulary()

        mainViewModel.vocabularyList.observe(this) { vocabularyList ->
            vocaAdapter.updateVocaList(vocabularyList)
        }

        mainBinding.fabAddWordSet.setOnClickListener {
            VocaDialogFragment().show(supportFragmentManager, "WordSetDialogFragment")
        }

        vocaAdapter.setItemClickListener { view, position ->
            val selectedWordSet = mainViewModel.vocabularyList.value?.get(position)
            selectedWordSet?.let {
                Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra("wordSetId", it.id.toString())
                    startActivity(this)
                }
            }
        }
    }

    /*override fun onInputSubmitted(owner: String, name: String, password: String) {
        mainViewModel.addWordSet(name, owner, password)
    }*/
}
