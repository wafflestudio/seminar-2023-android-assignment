package com.example.voca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voca.databinding.ActivityMainBinding
import com.example.voca.databinding.NewVocaListBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        binding.vocaListList.adapter = MyAdapter(viewModel.vocaList)
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
        /*
        binding2.newCancel.setOnClickListener {
            Log.d("aaaa","tlqkf")
        }*/


    }
    /*
    suspend fun io(){
        delay(1000L)
    }*/
}