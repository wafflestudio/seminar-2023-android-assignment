package com.jutak.assignment3

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jutak.assignment3.databinding.ActivityMainBinding
import com.jutak.assignment3.databinding.CreateWordlistDialogBinding
import dagger.hilt.android.AndroidEntryPoint
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
        adapter=MyMultiAdapter(viewModel.resbody,this)
        binding.wordlists.adapter=adapter
        binding.wordlists.layoutManager= LinearLayoutManager(this)

        viewModel.a()
        viewModel.liveresbody.observe(this){
            Log.d("aaaa",viewModel.liveresbody.value.toString())
            adapter.notifyDataSetChanged()
        }

        binding.createWordlist.setOnClickListener {
            val view=CreateWordlistDialogBinding.inflate(layoutInflater)
            val dialog= AlertDialog.Builder(this)
                .setView(view.root)
                .create()
            view.dialogOk.setOnClickListener{
                if (view.dialogNameinput.text.toString()!="" && view.dialogOwnerinput.text.toString()!="" && view.dialogPwinput.text.toString()!=""){
                    viewModel.postwordlist(MyModels.Data_newlist(view.dialogNameinput.text.toString(),view.dialogOwnerinput.text.toString(),view.dialogPwinput.text.toString()))
                    dialog.dismiss()
                }
            }
            dialog.show()
        }

        /*
        binding.text.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val response = api.word_list_create(MyModels.Data_newlist("Test2", "이현도", "123456"))
                response.enqueue(object : Callback<List<PostResult>> {
                    override fun onFailure(call: Call<List<PostResult>>, t: Throwable) {
                        // TODO:
                    }

                    override fun onResponse(
                        call: Call<List<PostResult>>,
                        response: Response<List<PostResult>>
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
        */
    }

    /*
    fun post(name:String,owner:String,pw:String):Unit{
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.word_list_create(MyModels.Data_newlist(name, owner, pw))
            response.enqueue(object : Callback<List<PostResult>> {
                override fun onFailure(call: Call<List<PostResult>>, t: Throwable) {
                    // TODO:
                }

                override fun onResponse(
                    call: Call<List<PostResult>>,
                    response: Response<List<PostResult>>
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
                        val n:Int=viewModel.resbody.value!!.size
                        viewModel.resbody.value!!.clear()
                        adapter.notifyItemRangeRemoved(0,n)
                        viewModel.resbody.value!!.addAll(response.body()!!)
                        Log.d("aaaa",viewModel.resbody.toString())
                    }
                }
            })
        }
    }
    */
}