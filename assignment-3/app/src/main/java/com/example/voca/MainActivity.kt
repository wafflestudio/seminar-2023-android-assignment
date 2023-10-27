package com.example.voca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voca.databinding.ActivityMainBinding
import com.example.voca.databinding.NewVocaListBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var restAPI: MyRestAPI

    private val viewModel :MyViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: NewVocaListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val adapter=MyAdapter(viewModel.vocaList,viewModel)
        binding.vocaListList.adapter = adapter
        binding.vocaListList.layoutManager = LinearLayoutManager(this)


        viewModel.getVocaListFromServer()

        viewModel.vocaList.observe(this){
            (binding.vocaListList.adapter as MyAdapter).notifyDataSetChanged()
        }
        //setContentView(binding2.root)

        binding.newList.setOnClickListener {
            binding2= NewVocaListBinding.inflate(layoutInflater)
            viewModel.openDialog(this,binding2)
        }
        adapter.setItemClickListener(object: MyAdapter.OnItemClickListener{

            override fun onClick(v: View, position: Int) {
                Log.d("aaaa","cj")
                val intent = Intent(this@MainActivity, InVocaActivity::class.java)
                intent.putExtra("id", position)
                startActivity(intent)
            }
        })
        /*
        binding2.newCancel.setOnClickListener {
            Log.d("aaaa","tlqkf")
        }*/


    }
    fun openVocaList(position:Int){
        var intent = Intent(this@MainActivity, InVocaActivity::class.java)
        startActivity(intent)
    }
    /*
    suspend fun io(){
        delay(1000L)
    }*/
}