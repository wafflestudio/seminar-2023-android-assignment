package com.jutak.assignment3

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment3.databinding.ActivityDetailBinding
import com.assignment3.databinding.WordAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var wordListAdapter: WordListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeView()
        handleUserInteractions()
    }

    private fun initializeView() {
        setupRecyclerView()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        viewModel.words.observe(this) { wordList: List<Word> ->
            wordListAdapter = WordListAdapter(wordList, viewModel::fetchVocabularySetDetails)
            binding.recyclerView.apply {
                adapter = wordListAdapter
                layoutManager = LinearLayoutManager(this@DetailActivity)
            }
        }
    }

    private fun setupClickListeners() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun handleUserInteractions() {
        intent.getStringExtra("wordSetId")?.let { id ->
            val wordSetId = id.toIntOrNull()
            wordSetId?.let(viewModel::fetchVocabularySetDetails)

            binding.editButton.setOnClickListener {
                val dialogBinding = WordAddBinding.inflate(layoutInflater)
                val builder = AlertDialog.Builder(this).apply {
                    setView(dialogBinding.root)
                }
                val dialog = builder.create().apply {
                    show()
                }

                dialogBinding.positiveButton.setOnClickListener {
                    if (wordSetId != null) {
                        val newWord = Word(
                            word = dialogBinding.editWord.text.toString(), // word 속성에 대한 값을 제공합니다.
                            spell = dialogBinding.editSpell.text.toString(), // spell 속성에 대한 값을 제공해야 합니다. dialogBinding에서 editSpell이라는 이름의 EditText가 존재한다고 가정하고 작성했습니다.
                            meaning = dialogBinding.editMeaning.text.toString(),
                            synonym = dialogBinding.editSynonym.text.toString(),
                            antonym = dialogBinding.editAntonym.text.toString(),
                            sentence = dialogBinding.editSentence.text.toString()
                        )
                        viewModel.addWordToWordSet(newWord, wordSetId)
                    }
                    dialog.dismiss()
                }
            }

            binding.deleteButton.setOnClickListener {
                val dialogBinding = WordAddBinding.inflate(layoutInflater)
                val builder = AlertDialog.Builder(this).apply {
                    setView(dialogBinding.root)
                }
                val dialog = builder.create().apply {
                    show()
                }

                dialogBinding.positiveButton.setOnClickListener {
                    dialog.dismiss()
                    navigateToMainActivity()
                }
                dialogBinding.negativeButton.setOnClickListener {
                    dialog.dismiss()
                }

            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@DetailActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
