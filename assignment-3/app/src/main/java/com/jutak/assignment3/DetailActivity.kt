package com.jutak.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityDetailBinding
import com.jutak.assignment3.databinding.DialogDeleteConfirmBinding
import com.jutak.assignment3.databinding.DialogPasswordBinding
import com.jutak.assignment3.databinding.DialogWordAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", 0)
        val setName = intent.getStringExtra("wordSetName")

        binding.title.text = setName

        val adapter = WordListAdapter(viewModel.wordList, this@DetailActivity)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getWordSetFromServer(id)

        viewModel.liveWordList.observe(this) {
            adapter.notifyDataSetChanged()
        }

        viewModel.liveHasPermission.observe(this) {
            if (it) {
                binding.editButton.text = "추가"
                binding.deleteButton.text = "삭제"
            }
            else {
                binding.editButton.text = "편집하기"
                binding.deleteButton.text = ""
            }
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.editButton.setOnClickListener {

            if (viewModel.liveHasPermission.value == true) {
                val dialogBinding = DialogWordAddBinding.inflate(layoutInflater)
                val builder = AlertDialog.Builder(this).apply {
                    setView(dialogBinding.root)
                }
                val dialog = builder.create().apply {
                    show()
                }

                dialogBinding.positiveButton.setOnClickListener {
                    viewModel.putWordToServer(Word(
                            dialogBinding.editWord.text.toString(),
                            dialogBinding.editMeaning.text.toString(),
                            dialogBinding.editSynonym.text.toString(),
                            dialogBinding.editAntonym.text.toString(),
                            dialogBinding.editSentence.text.toString()
                    ), id)
                    dialog.dismiss()
                }
                dialogBinding.negativeButton.setOnClickListener {
                    dialog.dismiss()
                }
            }
            else {
                val dialogBinding = DialogPasswordBinding.inflate(layoutInflater)
                val builder = AlertDialog.Builder(this).apply {
                    setView(dialogBinding.root)
                }
                val dialog = builder.create().apply {
                    show()
                }

                dialogBinding.positiveButton.setOnClickListener {
                    viewModel.checkPermissionFromServer(
                        PasswordInput(dialogBinding.editPassword.text.toString()), id
                    )
                    dialog.dismiss()
                }
                dialogBinding.negativeButton.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }

        binding.deleteButton.setOnClickListener {

            val dialogBinding = DialogDeleteConfirmBinding.inflate(layoutInflater)
            val builder = AlertDialog.Builder(this).apply {
                setView(dialogBinding.root)
            }
            val dialog = builder.create().apply {
                show()
            }

            dialogBinding.positiveButton.setOnClickListener {
                viewModel.deleteWordSetInServer(id)
                dialog.dismiss()
                val intent = Intent(this@DetailActivity, MainActivity::class.java)
                startActivity(intent)
            }
            dialogBinding.negativeButton.setOnClickListener {
                dialog.dismiss()
            }

        }

    }
}