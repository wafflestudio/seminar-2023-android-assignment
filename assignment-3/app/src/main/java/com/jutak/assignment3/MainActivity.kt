package com.jutak.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import com.jutak.assignment3.databinding.DialogCreateListBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: DialogCreateListBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2 = DialogCreateListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordListAdapter(viewModel.wordLists, this)
        binding.listRecyclerView.adapter = adapter
        binding.listRecyclerView.layoutManager =  LinearLayoutManager(this)

        viewModel.fetchWordLists()
        viewModel.wordLists.observe(this){
            adapter.notifyDataSetChanged()
        }

        binding.btn.setOnClickListener {
            val builder = AlertDialog.Builder(this).apply {
                setContentView(binding2.root)
            }

            val dialog = builder.create().apply {
                show()
            }

            binding2.confirm.setOnClickListener {
                viewModel.createWordList(
                    NewList(
                        binding2.eName.toString(),
                        binding2.eOwner.toString(),
                        binding2.ePassword.toString()
                    )
                )
                dialog.dismiss()
            }

            binding2.cancel.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}
