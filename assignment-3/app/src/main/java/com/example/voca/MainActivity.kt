package com.example.voca

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voca.databinding.ActivityMainBinding
import com.example.voca.databinding.NewVocaListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        val adapter=MyAdapter(viewModel.vocaList)
        binding.vocaListList.adapter = adapter
        binding.vocaListList.layoutManager = LinearLayoutManager(this)


        viewModel.getVocaListFromServer()

        viewModel.vocaList.observe(this){
            (binding.vocaListList.adapter as MyAdapter).notifyDataSetChanged()
        }
        //setContentView(binding2.root)

        binding.newList.setOnClickListener {
            binding2= NewVocaListBinding.inflate(layoutInflater)
            openDialog(this,binding2)
        }

        viewModel.inVocaList.observe(this){

            val intent = Intent(this@MainActivity, InVocaActivity::class.java)
            intent.putExtra("info", viewModel.inVocaList.value!!)
            startActivity(intent)
        }
        adapter.setItemClickListener(object: MyAdapter.OnItemClickListener{

            override fun onClick(v: View, position: Int) {
                Log.d("aaaa",position.toString())
                val intent = Intent(this@MainActivity, InVocaActivity::class.java)
                viewModel.getVocaListSpecificInfoFromServer(viewModel.vocaList.value!![position].id)
                /*
                lifecycleScope.launch(Dispatchers.IO){




                    withContext(Dispatchers.Main) {
                        intent.putExtra("info", viewModel.inVocaList.value!!)
                        intent.putExtra("id", position)
                        startActivity(intent)

                    }
                }

                */
                //Log.d("aaaa",viewModel.inVocaList.toString())


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
    fun openDialog(context: Context, binding: NewVocaListBinding){

        val dialog= Dialog(context)
        dialog.setContentView(binding.root)
        dialog.show()
        binding.newCancel.setOnClickListener {
            dialog.dismiss()
            Log.d("aaaa","시발 왜 ")
        }
        binding.newSave.setOnClickListener {
            val data:MyDataTypes.NewVocaList=MyDataTypes.NewVocaList(binding.inputOwnerName.text.toString(),binding.inputListName.text.toString(),binding.inputListPassword.text.toString())
            viewModel.postVocaListToServer(data)
            dialog.dismiss()
        }
    }
}