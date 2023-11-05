package com.jutak.assignment3

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.DetailWordListBinding
import com.jutak.assignment3.databinding.VocaInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class DetailVocaActivity: AppCompatActivity() {

    private lateinit var binding: DetailWordListBinding
    private lateinit var vocaInfoBinding:VocaInfoBinding

    private val viewModel:DetailVocaViewModel by viewModels();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DetailWordListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wordListID = intent.getIntExtra("id", 0)
        println(wordListID)

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main){
                viewModel.fetchWordList(wordListID)
            }
        }

        val VocaInfo = Dialog(this)
        vocaInfoBinding = VocaInfoBinding.inflate(layoutInflater)
        VocaInfo.setContentView(vocaInfoBinding.root)

        viewModel.wordList.observe(this){
            val adapter = DetailVocaMultiAdapter(it.wordList, onItemClick = {
                vocaInfoBinding.vocaSpell.text = it.spell
                vocaInfoBinding.vocaMean.text = it.meaning
                vocaInfoBinding.vocaSynonym.text = it.synonym
                vocaInfoBinding.vocaAntonym.text = it.antonym
                vocaInfoBinding.vocaSentence.text = it.sentence

                VocaInfo.show()

                vocaInfoBinding.exitButton.setOnClickListener{
                    VocaInfo.dismiss()
                }
            })
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this@DetailVocaActivity,MainActivity::class.java)
            startActivity(intent)
        }
    }
}