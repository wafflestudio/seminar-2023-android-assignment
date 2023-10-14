package com.jutak.assignment3

import android.os.Bundle
import android.util.Log
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

        viewModel.loadVoca()
        val adapter = MyAdapter(viewModel.vocaList.value!!)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.vocaList.observe(this) { vocaList ->
            Log.d("notifyDataSetChanged", "called")
            adapter.notifyDataSetChanged()
        }

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