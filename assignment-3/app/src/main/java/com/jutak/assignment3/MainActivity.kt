package com.jutak.assignment3

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import com.jutak.assignment3.databinding.AddWordListDialogViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addWordList.setOnClickListener {
            showAddWordListDialog(this)
        }

        val adapter = WordListAdapter(viewModel.wordLists)
        binding.wordListRecyclerView.adapter = adapter
        binding.wordListRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.wordLists.observe(this) {
            binding.wordListRecyclerView.adapter?.notifyDataSetChanged()
        }

    }

    private fun showAddWordListDialog(context: Context) {
        val dialogBinding = AddWordListDialogViewBinding.inflate(LayoutInflater.from(context))

        val ownerNameEditText = dialogBinding.ownerNameEditText
        val wordListNameEditText = dialogBinding.wordListNameEditText
        val passwordEditText = dialogBinding.passwordEditText

        val dialog = AlertDialog.Builder(context)
            .setTitle("새 단어장 만들기")
            .setView(dialogBinding.root)
            .setPositiveButton("확인") { dialog, _ ->
                val ownerName = ownerNameEditText.text.toString()
                val wordListName = wordListNameEditText.text.toString()
                val password = passwordEditText.text.toString()

                viewModel.createWordList(ownerName, wordListName, password)

                dialog.dismiss()
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

}