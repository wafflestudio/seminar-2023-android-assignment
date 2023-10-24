package com.jutak.assignment3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding
    private val viewModel:MyViewModel by viewModels()
    private lateinit var adapter:AWordListAdapter

    @Inject
    lateinit var api:MyRestAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AWordListAdapter(viewModel.wordlist, this)
        binding.words.adapter = adapter
        binding.words.layoutManager = LinearLayoutManager(this)

        Log.d("aaaa",intent.getIntExtra("id",0).toString())
        Log.d("aaaa",intent.getStringExtra("name").toString())

        viewModel.b(intent.getIntExtra("id",0), intent.getStringExtra("name")!!)
        viewModel.livewordlist.observe(this) {
            adapter.notifyDataSetChanged()
        }

        binding.back.setOnClickListener {
            Intent(this@DetailActivity,MainActivity::class.java).run{
                startActivity(this)
            }
        }
    }
}