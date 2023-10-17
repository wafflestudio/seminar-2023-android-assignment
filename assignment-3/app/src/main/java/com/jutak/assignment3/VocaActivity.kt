package com.jutak.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityVocaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VocaActivity: AppCompatActivity(), MyDialogVoca.DialogListener {
    private lateinit var binding: ActivityVocaBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVocaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyAdapter(viewModel.wordList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.loadVocaInfo(intent.getStringExtra("id"))

        //제목 쓰기
        viewModel.vocaInfo.observe(this){
            binding.title.text = viewModel.vocaInfo!!.value!!.name
        }

        //단어 업데이트
        viewModel.wordList.observe(this) { wordList ->
            adapter.notifyDataSetChanged()
        }

        //단어 세부사항
        adapter.setItemClickListener(object: MyAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                viewModel.loadWord(position)

                val dialog = MyDialogWord()
                dialog.viewModel = viewModel
                dialog.show(supportFragmentManager, "MyDialogWord")
            }
        })

        //화면 전환
        binding.backButton.setOnClickListener{
            val intent = Intent(this@VocaActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}