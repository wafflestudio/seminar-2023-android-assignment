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

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel:MyViewModel by viewModels()
    @Inject
    lateinit var api:MyRestAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.text.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val response = api.word_list_create(Data1("Test1", "이현도", "123456"))
                response.enqueue(object : Callback<PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        // TODO:
                    }

                    override fun onResponse(
                        call: Call<PostResult>,
                        response: Response<PostResult>
                    ) {
                        val responseBody = response.body()
                        Log.d("aaaa","s")
                        binding.text.text=responseBody.toString()
                    }
                })
            }
        }
        binding.text2.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val response2=api.word_list_read(9)
                response2.enqueue(object : Callback<GetModel> {
                    override fun onFailure(call: Call<GetModel>, t: Throwable) {
                        // TODO:
                    }

                    override fun onResponse(
                        call: Call<GetModel>,
                        response: Response<GetModel>
                    ) {
                        //val responseBody2 = response2
                        Log.d("aaaa","ss")
                        binding.text2.text=response.body().toString()
                    }
                })
            }
        }
    }
}