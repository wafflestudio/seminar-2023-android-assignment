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
                val response = api.word_list_create(Data1("Test2", "이현도", "123456"))
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
                        Log.d("aaaa","ss")
                        binding.text2.text=response.body().toString()
                    }
                })
            }
        }
        binding.text3.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val response3=api.word_list_update(
                    Data2("123456",
                        Word("apple","사과",null,null,null)
                    ),9
                )
                response3.enqueue(object : Callback<GetModel> {
                    override fun onFailure(call: Call<GetModel>, t: Throwable) {
                        // TODO:
                    }

                    override fun onResponse(
                        call: Call<GetModel>,
                        response: Response<GetModel>
                    ) {
                        Log.d("aaaa","sss")
                        binding.text3.text=response.body().toString()
                    }
                })
            }
        }
        binding.text4.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val response4=api.word_list_delete(pwData("123456"),13)
                response4.enqueue(object : Callback<DeleteResult> {
                    override fun onFailure(call: Call<DeleteResult>, t: Throwable) {
                        // TODO:
                    }

                    override fun onResponse(
                        call: Call<DeleteResult>,
                        response: Response<DeleteResult>
                    ) {
                        Log.d("aaaa","ssss")
                        binding.text4.text=response.body().toString()
                    }
                })
            }
        }
        binding.text5.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val response5=api.word_list_permission_create(pwData("123456"),9)
                response5.enqueue(object : Callback<PerResult> {
                    override fun onFailure(call: Call<PerResult>, t: Throwable) {
                        // TODO:
                    }

                    override fun onResponse(
                        call: Call<PerResult>,
                        response: Response<PerResult>
                    ) {
                        Log.d("aaaa","sssss")
                        binding.text5.text=response.body().toString()
                    }
                })
            }
        }
    }
}