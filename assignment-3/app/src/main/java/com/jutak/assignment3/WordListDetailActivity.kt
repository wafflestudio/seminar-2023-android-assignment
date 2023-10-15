package com.jutak.assignment3

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityWordListDetailBinding
import com.jutak.assignment3.databinding.EditWordListDialogViewBinding
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
            finish()
        }

        binding.editWordListButton.setOnClickListener {
            showEditWordListDialog(this)
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

        viewModel.permission.observe(this) {
            if (it == false) {
                return@observe
            }
            binding.editWordListButton.visibility = View.INVISIBLE
            binding.addWordButton.visibility = View.VISIBLE
            binding.deleteWordListButton.visibility = View.VISIBLE
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

    private fun showEditWordListDialog(context: Context) {
        val dialogBinding = EditWordListDialogViewBinding.inflate(LayoutInflater.from(context))

        val dialog = AlertDialog.Builder(context)
            .setTitle("단어장 편집하기")
            .setView(dialogBinding.root)
            .setPositiveButton("확인") { dialog, _ ->
                val password = dialogBinding.passwordEditText.text.toString()
                // check password
                viewModel.checkPermission(password)
                dialog.dismiss()
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }

        dialog.show()
    }

}
