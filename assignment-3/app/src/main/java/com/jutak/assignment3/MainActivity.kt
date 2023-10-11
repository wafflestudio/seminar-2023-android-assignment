package com.jutak.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import com.jutak.assignment3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import android.util.Log

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MyDialogVoca.DialogListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadVoca()
        viewModel.vocaList.observe(this, { vocaList ->
            val adapter = MyAdapter(viewModel.vocaList.value!!)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        })

        binding.addButton.setOnClickListener{
            val dialog = MyDialogVoca()
            dialog.show(supportFragmentManager, "MyDialogVoca")
        }
    }

    override fun onInputReceived(owner:String, name:String, pw:String){
        viewModel.updateVoca(owner, name, pw)
        Log.d("dialog input", "passed")
    }
}