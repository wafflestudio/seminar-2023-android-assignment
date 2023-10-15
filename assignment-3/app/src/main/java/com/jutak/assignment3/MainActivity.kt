package com.jutak.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jutak.assignment3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var api: MyRestAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)

//        val response = api.getVocaBookList()
//        response.enqueue(object : Callback<List<Vocabook>>{
//            override fun onResponse(call: Call<List<Vocabook>>, response: Response<List<Vocabook>>) {
//                Log.d("success",response.body().toString())
//            }
//
//            override fun onFailure(call: Call<List<Vocabook>>, t: Throwable) {
//                Log.d("fail", t.toString())
//            }
//        })

//        CoroutineScope(Dispatchers.IO).launch {
//            val response = api.getVocaBookListSuspend()
//            withContext(Dispatchers.Main) {
//                binding.text.text = "이름 : ${response[0].id}, 나이 : ${response[0].name}"
//            }
//        }


    }
}