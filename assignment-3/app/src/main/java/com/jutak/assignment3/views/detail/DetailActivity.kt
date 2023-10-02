package com.jutak.assignment3.views.detail

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.data.model.Word
import com.jutak.assignment3.data.model.WordList
import com.jutak.assignment3.databinding.ActivityDetailBinding
import com.jutak.assignment3.databinding.DialogDeleteWordListBinding
import com.jutak.assignment3.databinding.DialogEditPermissionBinding
import com.jutak.assignment3.databinding.DialogNewWordBinding
import com.jutak.assignment3.databinding.DialogWordDetailBinding
import com.jutak.assignment3.util.ApiOnError
import com.jutak.assignment3.util.launchApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var apiOnError: ApiOnError

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding
    private lateinit var wordsListAdapter: WordsAdapter
    private val wordList get() = intent.extras?.getParcelable("wordList") ?: WordList.Default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        wordsListAdapter = WordsAdapter(
            onItemClick = { idx, word ->
                showWordDetail(word)
            }
        )
        wordsListAdapter.submitList(wordList.wordList)
        binding.wordsRecyclerView.adapter = wordsListAdapter
        binding.wordsRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.name.text = wordList.name
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.editPermissionButton.setOnClickListener {
            showEditPermissionDialog()
        }
        binding.addWordButton.setOnClickListener {
            showNewWordDialog()
        }
        binding.deleteButton.setOnClickListener {
            showDeleteDialog()
        }

        viewModel.editMode.observe(this) { editMode ->
            if (editMode) {
                binding.addWordButton.visibility = View.VISIBLE
                binding.deleteButton.visibility = View.VISIBLE
                binding.editPermissionButton.visibility = View.GONE
            } else {
                binding.addWordButton.visibility = View.GONE
                binding.deleteButton.visibility = View.GONE
                binding.editPermissionButton.visibility = View.VISIBLE
            }
        }
    }

    private fun showEditPermissionDialog() {
        Dialog(this).apply {
            val alertDialogBinding = DialogEditPermissionBinding.inflate(layoutInflater)
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
                        viewModel.checkWordListEditPermission(
                            listId = wordList.id,
                            password = alertDialogBinding.passwordEditText.text.toString(),
                        )
                        withContext(Dispatchers.Main) {
                            if (viewModel.editMode.value == true) {
                                hide()
                            } else {
                                Toast.makeText(this@DetailActivity, "비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
            show()
        }
    }

    private fun showNewWordDialog() {
        Dialog(this).apply {
            val alertDialogBinding = DialogNewWordBinding.inflate(layoutInflater)
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
                        viewModel.addNewWordToWordList(
                            listId = wordList.id,
                            password = viewModel.password,
                            word = Word(
                                alertDialogBinding.wordSpellEditText.text.toString(),
                                alertDialogBinding.wordMeaningEditText.text.toString(),
                                alertDialogBinding.wordSynonymEditText.text.toString(),
                                alertDialogBinding.wordAntonymEditText.text.toString(),
                                alertDialogBinding.wordSentenceEditText.text.toString(),
                            )
                        ).let {
                            withContext(Dispatchers.Main) {
                                wordsListAdapter.submitList(it.wordList)
                                hide()
                            }
                        }
                    }
                }
            }
            show()
        }
    }

    private fun showDeleteDialog() {
        Dialog(this).apply {
            val alertDialogBinding = DialogDeleteWordListBinding.inflate(layoutInflater)
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
                        viewModel.deleteWordList(
                            listId = wordList.id,
                            password = viewModel.password,
                        )
                        withContext(Dispatchers.Main) {
                            hide()
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                    }
                }
            }
            show()
        }
    }

    private fun showWordDetail(word: Word) {
        Dialog(this).apply {
            val alertDialogBinding = DialogWordDetailBinding.inflate(layoutInflater)
            this.setContentView(alertDialogBinding.root)
            this.window?.setLayout(
                resources.displayMetrics.widthPixels * 0.90.roundToInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            alertDialogBinding.spell.text = word.spell
            alertDialogBinding.meaning.text = word.meaning
            alertDialogBinding.antonym.text = word.antonym ?: ""
            alertDialogBinding.synonym.text = word.synonym ?: ""
            alertDialogBinding.example.text = word.sentence ?: ""

            alertDialogBinding.positiveButton.setOnClickListener {
                hide()
            }
            show()
        }
    }
}