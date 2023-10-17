package com.jutak.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordListAdapter(::onWordListClick)
        binding.recyclerViewWordList.adapter = adapter
        binding.recyclerViewWordList.layoutManager = LinearLayoutManager(this)


        viewModel.fetchWordLists()

        viewModel.wordLists.observe(this, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
        val wordListDialog = WordListDialog(viewModel)
        binding.wordListCreateBtn.setOnClickListener {
            wordListDialog.show(supportFragmentManager, "wordListDialogShow")
        }

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
            if (result.resultCode == RESULT_OK) {
                Toast.makeText(this,"단어장 리스트로 돌아왔다", Toast.LENGTH_SHORT).show()
                viewModel.fetchWordLists()
            }
        }

    }

    private fun onWordListClick(id : Int){
        val intent = Intent(this, WordActivity::class.java)
        intent.putExtra("word_list_id", id)
        activityResultLauncher.launch(intent)
    }
}