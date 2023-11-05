package com.jutak.assignment3.views.main

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import com.jutak.assignment3.databinding.DialogNewWordListBinding
import com.jutak.assignment3.util.ApiOnError
import com.jutak.assignment3.util.launchApi
import com.jutak.assignment3.views.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apiOnError: ApiOnError

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { _ ->
            CoroutineScope(Dispatchers.IO).launch {
                launchApi(apiOnError) {
                    viewModel.fetchRemoteWordLists()
                }
            }
        }

        val wordListsAdapter = WordListsAdapter(
            onItemClick = { id ->
                CoroutineScope(Dispatchers.IO).launch {
                    launchApi(apiOnError) {
                        val wordList = viewModel.fetchWordListDetail(id)
                        withContext(Dispatchers.Main) {
                            activityResultLauncher.launch(
                                Intent(this@MainActivity, DetailActivity::class.java)
                                    .putExtra("wordList", wordList)
                            )
                        }
                    }
                }
            }
        )
        binding.wordListsRecyclerView.adapter = wordListsAdapter
        binding.wordListsRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.remoteList.observe(this) {
            wordListsAdapter.submitList(it)
        }

        binding.addWordListButton.setOnClickListener {
            showNewWordListDialog()
        }

        CoroutineScope(Dispatchers.IO).launch {
            launchApi(apiOnError) {
                viewModel.fetchRemoteWordLists()
            }
        }
    }

    private fun showNewWordListDialog() {
        Dialog(this).apply {
            val alertDialogBinding = DialogNewWordListBinding.inflate(layoutInflater)
            this.setContentView(alertDialogBinding.root)
            this.window?.setLayout(
                resources.displayMetrics.widthPixels * 0.90.roundToInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            alertDialogBinding.negativeButton.setOnClickListener {
                hide()
            }
            alertDialogBinding.positiveButton.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    launchApi(apiOnError) {
                        viewModel.postNewWordList(
                            name = alertDialogBinding.wordListTitleEditText.text.toString(),
                            owner = alertDialogBinding.wordListOwnerEditText.text.toString(),
                            password = alertDialogBinding.wordListPwEditText.text.toString(),
                        )
                        withContext(Dispatchers.Main) {
                            hide()
                        }
                    }
                }
            }
            show()
        }
    }
}