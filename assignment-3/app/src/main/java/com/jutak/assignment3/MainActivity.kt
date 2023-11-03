package com.jutak.assignment3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment3.databinding.ActivityMainBinding
import com.assignment3.databinding.WordAddBinding
import dagger.hilt.android.AndroidEntryPoint
import com.jutak.assignment3.MainViewModel.PostWordSetInput


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var wordSetAdapter: WordSetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        setupRecyclerView()
        observeWordSetList()
        setupEditButton()

        viewModel.fetchVocabularySets()
    }


    private fun setupRecyclerView() {
        wordSetAdapter = WordSetAdapter(mutableListOf(), this)
        mainBinding.recyclerView.apply {
            adapter = wordSetAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }


    private fun observeWordSetList() {
        viewModel.liveWordSets.observe(this) { vocabularySetList ->
            val wordSets = vocabularySetList.map { vocabSet ->
                WordSetAdapter.WordSet(
                    id = vocabSet.id,
                    name = vocabSet.setName,
                    owner = vocabSet.ownerName
                )
            }
            wordSetAdapter.updateData(wordSets)
        }
    }


    private fun setupEditButton() {
        mainBinding.editButton.setOnClickListener {
            showAddWordSetDialog()
        }
    }

    private fun showAddWordSetDialog() {
        val addWordSetBinding = WordAddBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(addWordSetBinding.root)
            .create()

        addWordSetBinding.positiveButton.setOnClickListener {
            val owner = addWordSetBinding.editOwner.text.toString()
            val setName = addWordSetBinding.editSetName.text.toString()
            val password = addWordSetBinding.editPassword.text.toString()

            val postInput = PostWordSetInput(
                setName = setName,
                ownerName = owner,
                password = password
            )

            viewModel.postWordSetToServer(postInput)  // Changed here
            dialog.dismiss()
        }

        addWordSetBinding.negativeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
