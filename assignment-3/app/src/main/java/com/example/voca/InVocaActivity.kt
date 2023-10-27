package com.example.voca

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voca.databinding.ActivityInVocaBinding


class InVocaActivity:AppCompatActivity() {
    private lateinit var binding:ActivityInVocaBinding

    private val viewModel :MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityInVocaBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val intent = intent
        val id=intent.getIntExtra("id", 0)

        val adapter=MyAdapter(viewModel.inVocaList_Voca,viewModel)
        binding.vocaList.adapter = adapter
        binding.vocaList.layoutManager = LinearLayoutManager(this)
        //viewModel.getVocaListSpecificInfoFromServer(id)
        /*
        val adapter=MyAdapter(viewModel.inVocaList_Voca,viewModel)
        binding.vocaList.adapter = adapter
        binding.vocaList.layoutManager = LinearLayoutManager(this)

        viewModel.inVocaList_Voca.observe(this){
            (binding.vocaList.adapter as MyAdapter).notifyDataSetChanged()
        }
        viewModel.inVocaList.observe(this){
            binding.vocaListName.text=viewModel.inVocaList.value!!.name
        }

        */
    }
}