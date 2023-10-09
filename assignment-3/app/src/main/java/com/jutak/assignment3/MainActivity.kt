package com.jutak.assignment3

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jutak.assignment3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel:MyViewModel by viewModels()
    @Inject
    lateinit var api:MyRestAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("aaaa","a")

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            val response=api.word_list_create(Data("a","b","c"))
            response.enqueue(object :Callback<PostResult>{
                override fun onFailure(call: Call<PostResult>,t:Throwable){
                    // TODO:
                }
                override fun onResponse(call:Call<PostResult>,response:Response<PostResult>){
                    val responseBody=response.body()
                    // TODO:
                }
            })
        }
    }
}