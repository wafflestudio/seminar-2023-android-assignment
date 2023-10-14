package com.jutak.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MyDialogVoca.DialogListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyAdapter(viewModel.vocaList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //단어장 로드
        viewModel.loadVoca()

        //단어장 업데이트
        viewModel.vocaList.observe(this) { vocaList ->
            adapter.notifyDataSetChanged()
        }

        //단어장 추가
        binding.addButton.setOnClickListener{
            val dialog = MyDialogVoca()
            dialog.show(supportFragmentManager, "MyDialogVoca")
        }

        //단어장 화면 전환
        adapter.setItemClickListener(object: MyAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val intent = Intent(this@MainActivity, VocaActivity::class.java)
                intent.putExtra("id", viewModel.vocaList.value!![position].id)
                startActivity(intent)
            }
        })
    }

    override fun onInputReceived(name:String, owner:String, pw:String){
        viewModel.updateVoca(name, owner, pw)
    }
}