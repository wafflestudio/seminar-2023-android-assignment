package com.jutak.assignment3

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel:MyViewModel by viewModels()
    private lateinit var adapter:MyMultiAdapter

    @Inject
    lateinit var api:MyRestAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //var resb=emptyList<MyModels.Wordlists>()
        //val adapter=MyMultiAdapter(list=listOf(MyMultiData.TypeA(resb)),this)
        adapter=MyMultiAdapter(list=listOf(MyMultiData.TypeA(viewModel.resbody)),this)
        binding.wordlists.adapter=adapter
        binding.wordlists.layoutManager= LinearLayoutManager(this)

        getlists()

        binding.createWordlist.setOnClickListener {
            val view=LayoutInflater.from(this).inflate(R.layout.create_wordlist_dialog,null)
            val dialog=AlertDialog.Builder(this)
                .setView(view)
                .create()
            val dialog_button_ok=view.findViewById<TextView>(R.id.dialog_ok)
            val dialog_button_cancel=view.findViewById<TextView>(R.id.dialog_cancel)
            val dialog_input_owner=view.findViewById<TextView>(R.id.dialog_ownerinput)
            val dialog_input_name=view.findViewById<TextView>(R.id.dialog_nameinput)
            val dialog_input_pw=view.findViewById<TextView>(R.id.dialog_pwinput)
            dialog_button_ok.setOnClickListener {
                if (dialog_input_owner.text!="" && dialog_input_name.text!="" && dialog_input_pw.text!=""){
                    post(dialog_input_name.text.toString(),dialog_input_owner.text.toString(),dialog_input_pw.text.toString())
                    dialog.dismiss()
                    getlists()
                }
            }
            dialog.show()
        }

        binding.text.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val response = api.word_list_create(MyModels.Data_newlist("Test2", "이현도", "123456"))
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
                response2.enqueue(object : Callback<MyModels.Awordlist> {
                    override fun onFailure(call: Call<MyModels.Awordlist>, t: Throwable) {
                        // TODO:
                    }

                    override fun onResponse(
                        call: Call<MyModels.Awordlist>,
                        response: Response<MyModels.Awordlist>
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
                    MyModels.Data_putword(
                        "123456",
                        MyModels.Word("단어 2", "뜻 2", null, null, null)
                    ),9
                )
                response3.enqueue(object : Callback<MyModels.Awordlist> {
                    override fun onFailure(call: Call<MyModels.Awordlist>, t: Throwable) {
                        // TODO:
                    }

                    override fun onResponse(
                        call: Call<MyModels.Awordlist>,
                        response: Response<MyModels.Awordlist>
                    ) {
                        Log.d("aaaa","sss")
                        binding.text3.text=response.body().toString()
                    }
                })
            }
        }
        binding.text4.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val response4=api.word_list_delete(MyModels.Datapw("123456"),13)
                response4.enqueue(object : Callback<MyModels.DeleteResult> {
                    override fun onFailure(call: Call<MyModels.DeleteResult>, t: Throwable) {
                        // TODO:
                    }

                    override fun onResponse(
                        call: Call<MyModels.DeleteResult>,
                        response: Response<MyModels.DeleteResult>
                    ) {
                        Log.d("aaaa","ssss")
                        binding.text4.text=response.body().toString()
                    }
                })
            }
        }
        binding.text5.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val response5=api.word_list_permission_create(MyModels.Datapw("123456"),9)
                response5.enqueue(object : Callback<MyModels.PerResult> {
                    override fun onFailure(call: Call<MyModels.PerResult>, t: Throwable) {
                        // TODO:
                    }

                    override fun onResponse(
                        call: Call<MyModels.PerResult>,
                        response: Response<MyModels.PerResult>
                    ) {
                        Log.d("aaaa","sssss")
                        binding.text5.text=response.body().toString()
                    }
                })
            }
        }

    }

    fun post(name:String,owner:String,pw:String):Unit{
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.word_list_create(MyModels.Data_newlist(name, owner, pw))
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

    fun getlists():Unit{
        CoroutineScope(Dispatchers.IO).launch {
            val response6=api.word_lists_list()
            response6.enqueue(object : Callback<List<MyModels.Wordlists>> {
                override fun onFailure(call: Call<List<MyModels.Wordlists>>, t: Throwable) {
                    // TODO:
                }

                override fun onResponse(
                    call: Call<List<MyModels.Wordlists>>,
                    response: Response<List<MyModels.Wordlists>>
                ) {
                    Log.d("aaaa",response.body().toString())
                    if (response.body()!=null){
                        viewModel.resbody.clear()
                        viewModel.resbody.addAll(response.body()!!)
                        Log.d("aaaa",viewModel.resbody.toString())
                        adapter.notifyDataSetChanged()
                    }
                }
            })
        }
    }
}