package com.jutak.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityWordBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.properties.Delegates


@AndroidEntryPoint
class WordActivity(): AppCompatActivity(), EditListDialog.EditListDialogListener, DeleteListDialog.DeleteListDialogListener, AddWordDialog.AddWordDialogListener {

    private lateinit var binding: ActivityWordBinding
    private val viewModel : WordViewModel by viewModels()
    private var wordListId =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        wordListId = intent.getIntExtra("word_list_id",0)

        val adapter = WordAdapter(::onWordClick)
        binding.recyclerViewWords.adapter = adapter
        binding.recyclerViewWords.layoutManager = LinearLayoutManager(this)

        viewModel.fetchWords(wordListId)

        viewModel.words.observe(this, Observer {
            binding.wordListTitle.text = it.name
            adapter.submitList(it.words)
            adapter.notifyDataSetChanged()
        })

        binding.backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            setResult(RESULT_OK,intent)
            finish()
        }

        val editListDialog = EditListDialog()
        binding.notEditable.setOnClickListener {
            editListDialog.show(supportFragmentManager, "EditListDialogShow")
        }

        viewModel.valid.observe(this, Observer {
            if(viewModel.valid.value==true){
                binding.deleteBtn.visibility = View.VISIBLE
                binding.plusBtn.visibility = View.VISIBLE
                binding.notEditable.visibility = View.GONE
            }
        })

        val deleteListDialog = DeleteListDialog()
        binding.deleteBtn.setOnClickListener {
            deleteListDialog.show(supportFragmentManager, "DeleteListDialogShow")
        }


        val addWordDialog = AddWordDialog()
        binding.plusBtn.setOnClickListener {
            addWordDialog.show(supportFragmentManager, "AddWordDialogShow")
        }



    }

    private fun onWordClick(word : Word){
        val wordDialog = WordDialog(word)
        wordDialog.show(supportFragmentManager, "wordDialogShow")
    }

    override fun onDialogPasswordReceived(password: String) {
        val response = viewModel.confirmPassword(this, wordListId,password)
    }

    override fun deleteDialog() {
        val response = viewModel.deleteDialog(wordListId)
        val intent = Intent(this, MainActivity::class.java)
        setResult(RESULT_OK,intent)
        finish()
    }

    override fun addWord(newWord : Word){
        val response = viewModel.addWord(wordListId, newWord)
    }
}