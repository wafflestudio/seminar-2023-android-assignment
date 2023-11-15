package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    val viewModel:MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slots:List<TextView> =listOf(binding.slot11,binding.slot12,binding.slot13,binding.slot21,
            binding.slot22,binding.slot23,binding.slot31,binding.slot32,binding.slot33)
        for (i in 0..<9){
            slots[i].setOnClickListener {viewModel.process(i+1)}
        }

        val adapter=Adapter(list=viewModel.history,this)
        binding.history.adapter=adapter
        binding.history.layoutManager=LinearLayoutManager(this)
        viewModel.reset()

        viewModel.livegamekey.observe(this){
            when (it!=0){
                true -> binding.resetbutton.text="한판 더"
                false -> binding.resetbutton.text="초기화"
            }
        }

        viewModel.liveboard.observe(this){
            for(i in 0..<9){
                slots[i].text=when(viewModel.board[i]){
                    1 -> "O"
                    0 -> ""
                    -1 -> "X"
                    else -> "error"
                }
            }
            Log.d("aaaa",viewModel.history.toString())
            adapter.notifyDataSetChanged()
        }

        binding.resetbutton.setOnClickListener{viewModel.reset()}
        binding.drawerbutton.setOnClickListener{binding.root.openDrawer(binding.drawer)}
    }
}
