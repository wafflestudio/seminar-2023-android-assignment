package com.jutak.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import com.jutak.assignment3.databinding.DialogWordSetAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordSetListAdapter(viewModel.wordSetList, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.liveHasPermission.value = false
        viewModel.getWordSetListFromServer()

        viewModel.liveWordSetList.observe(this) {
            adapter.notifyDataSetChanged()
        }

        binding.editButton.setOnClickListener {

            val dialogBinding = DialogWordSetAddBinding.inflate(layoutInflater)

            val builder = AlertDialog.Builder(this).apply {
                setView(dialogBinding.root)
            }
            val dialog = builder.create().apply {
                show()
            }

            dialogBinding.positiveButton.setOnClickListener {
                viewModel.postWordSetToServer(
                    PostWordSetInput(
                        dialogBinding.editName.text.toString(),
                        dialogBinding.editOwner.text.toString(),
                        dialogBinding.editPassword.text.toString()
                    )
                )
                dialog.dismiss()
            }
            dialogBinding.negativeButton.setOnClickListener {
                dialog.dismiss()
            }
        }

    }
}