package com.jutak.assignment3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityWordListDetailBinding
import com.jutak.assignment3.databinding.WordDetailDialogViewBinding
import com.jutak.assignment3.model.Word
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordListDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWordListDetailBinding

    private val viewModel: WordListDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWordListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", -1)
        if (id > 0 && id != viewModel.wordListBrief?.id) {
            viewModel.fetchWord(id)
        }

        binding.goBackButton.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
            }
        }

        val adapter = WordListDetailAdapter(viewModel.words) { word ->
            showWordDetailDialog(this, word)
        }
        binding.wordListDetailRecyclerView.adapter = adapter
        binding.wordListDetailRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.words.observe(this) {
            binding.wordListHeader.text = viewModel.wordListBrief?.name
            binding.wordListDetailRecyclerView.adapter?.notifyDataSetChanged()
        }

    }

    private fun showWordDetailDialog(context: Context, word: Word) {
        val dialogBinding = WordDetailDialogViewBinding.inflate(LayoutInflater.from(context))

        dialogBinding.spell.text = word.spell
        dialogBinding.meaning.text = word.meaning
        dialogBinding.synonym.text = word.synonym
        dialogBinding.antonym.text = word.antonym
        dialogBinding.sentence.text = word.synonym

        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}