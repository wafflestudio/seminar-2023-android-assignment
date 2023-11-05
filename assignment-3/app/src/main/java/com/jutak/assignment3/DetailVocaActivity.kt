package com.jutak.assignment3

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.DetailWordListBinding
import com.jutak.assignment3.databinding.PasswordBinding
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
    private lateinit var passwordBinding: PasswordBinding

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

        //Voca Information Screen
        val VocaInfo = Dialog(this)
        vocaInfoBinding = VocaInfoBinding.inflate(layoutInflater)
        VocaInfo.setContentView(vocaInfoBinding.root)

        //Put Password Screen
        val passInput = Dialog(this)
        passwordBinding = PasswordBinding.inflate(layoutInflater)
        passInput.setContentView(passwordBinding.root)


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

        binding.editButton.setOnClickListener {

            passInput.show()

            passwordBinding.cancel.setOnClickListener { passInput.dismiss() }
            passwordBinding.check.setOnClickListener {
                val password = passwordBinding.passwordInfoPasswordType.text.toString()

                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main) {
                        viewModel.checkPermission(wordListID, Password(password))
                    }
                }
                passInput.dismiss()
            }

            viewModel.permission.observe(this){
                if(it){
                    binding.editButton.visibility = View.INVISIBLE
                    binding.deleteButton.visibility = View.VISIBLE
                    binding.newVocButton.visibility = View.VISIBLE
                }
            }
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this@DetailVocaActivity,MainActivity::class.java)
            startActivity(intent)
        }
    }
}