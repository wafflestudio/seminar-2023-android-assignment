package com.jutak.assignment3

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import com.jutak.assignment3.databinding.NewVocaBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newVocBinding: NewVocaBinding

    private val viewModel: MainViewModel by viewModels();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newVoc = Dialog(this)
        newVocBinding = NewVocaBinding.inflate(layoutInflater)
        newVoc.setContentView(newVocBinding.root)


        viewModel.returnData().observe(this){
            val adapter =MyMultiAdapter(it)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }

        binding.newVocButton.setOnClickListener{
            newVoc.show()
            newVocBinding.cancel.setOnClickListener {
                newVoc.dismiss()
            }
            newVocBinding.check.setOnClickListener {
            }
        }
    }
}