package com.example.voca

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voca.databinding.ActivityInVocaBinding
import com.example.voca.databinding.NewVocaListBinding
import com.example.voca.databinding.SpecificVocaListBinding


class InVocaActivity:AppCompatActivity() {
    private lateinit var binding:ActivityInVocaBinding
    private lateinit var binding2:SpecificVocaListBinding
    private val _inVocaList: MutableLiveData<MyDataTypes.VocaListSpecificInfo> = MutableLiveData()
    val inVocaList: LiveData<MyDataTypes.VocaListSpecificInfo> = _inVocaList

    private val _inVocaList_Voca: MutableLiveData<List<MyDataTypes.Voca>> = MutableLiveData(listOf())
    val inVocaList_Voca: LiveData<List<MyDataTypes.Voca>> = _inVocaList_Voca

    //  private val viewModel: MyViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityInVocaBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //val id=intent.getIntExtra("id", 0)


        val simpleData = intent.getParcelableExtra<MyDataTypes.VocaListSpecificInfo>("info")!!
        //val simpleData=MyDataTypes.VocaListSpecificInfo(id,"2","3", listOf(MyDataTypes.Voca("1","2","3","4","5")))
        Log.d("aaaa",simpleData.id.toString())
        _inVocaList.value=simpleData
        _inVocaList_Voca.value=simpleData.word_list
        binding.vocaListName.text=simpleData.name
        val adapter=MyAdapter2(inVocaList_Voca,inVocaList.value!!)
        binding.vocaList.adapter = adapter
        binding.vocaList.layoutManager = LinearLayoutManager(this)
        inVocaList_Voca.observe(this){
            (binding.vocaList.adapter as MyAdapter2).notifyDataSetChanged()
        }
        binding.back.setOnClickListener {
            finish()
        }
        //viewModel.getVocaListSpecificInfoFromServer(id)
        /*
        val adapter=MyAdapter(viewModel.inVocaList_Voca,viewModel)
        binding.vocaList.adapter = adapter
        binding.vocaList.layoutManager = LinearLayoutManager(this)


        viewModel.inVocaList.observe(this){
            binding.vocaListName.text=viewModel.inVocaList.value!!.name
        }

        */
        adapter.setItemClickListener(object: MyAdapter2.OnItemClickListener{

            override fun onClick2(v: View, position: Int) {
                Log.d("aaaa",position.toString())
                //intent.putExtra("info", viewModel.inVocaList.value!!)
                //viewModel.getVocaListSpecificInfoFromServer(position)
                //Log.d("aaaa",viewModel.inVocaList.toString())

                openDialog(inVocaList_Voca.value!![position])

            }
        })

    }
    fun openDialog(data:MyDataTypes.Voca){

        val dialog= Dialog(this)
        binding2= SpecificVocaListBinding.inflate(layoutInflater)
        dialog.setContentView(binding2.root)
        dialog.show()

        binding2.spell.text=data.spell
        binding2.meaning.text=data.meaning
        binding2.an.text=data.antonym
        binding2.sy.text=data.synonym
        binding2.centense.text=data.sentence
        binding2.close.setOnClickListener {
            dialog.dismiss()
            Log.d("aaaa","시발 왜 ")
        }
    }
}