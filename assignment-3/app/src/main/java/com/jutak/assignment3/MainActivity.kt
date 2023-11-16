package com.jutak.assignment3

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import com.jutak.assignment3.databinding.NewVocaBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newVocAddBinding: NewVocaBinding

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main){
                viewModel.fetchWordBooks()
            }
        }

        val adapter =MyMultiAdapter(onItemClick = {id ->
            val intent = Intent(this@MainActivity,DetailVocaActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.wordBookList.observe(this){
            adapter.setWordBookList(viewModel.wordBookList.value)
        }

        val newVocAdd = Dialog(this)
        newVocAddBinding = NewVocaBinding.inflate(layoutInflater)
        newVocAdd.setContentView(newVocAddBinding.root)

        binding.newVocButton.setOnClickListener{
            newVocAdd.show()
            newVocAddBinding.cancel.setOnClickListener {
                newVocAdd.dismiss()
            }
            newVocAddBinding.check.setOnClickListener {
                val owner = newVocAddBinding.newHostType.text.toString()
                val name = newVocAddBinding.newListType.text.toString()
                val pass = newVocAddBinding.newPassType.text.toString()

                CoroutineScope(Dispatchers.IO).launch{
                    withContext(Dispatchers.Main){
                        viewModel.addWordBook(owner, name, pass)
                    }
                }

                newVocAdd.dismiss()
            }
        }

    }
}