package com.example.voca

import android.app.Dialog
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voca.databinding.NewVocaListBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.Body
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val api:MyRestAPI,
): ViewModel(){

    private val _vocaList: MutableLiveData<List<MyDataTypes.VocaListInfo>> = MutableLiveData(listOf())
    val vocaList: LiveData<List<MyDataTypes.VocaListInfo>> = _vocaList

    fun getVocaListFromServer(){
        viewModelScope.launch(Dispatchers.IO){
            val response=api.getVocaLists()
            withContext(Dispatchers.Main) {
                _vocaList.value = response
            }
            //Log.d("aaaa",response.toString())
        }
    }
    fun postVocaListToServer(@Body data : MyDataTypes.NewVocaList){
        viewModelScope.launch(Dispatchers.IO){
            val response=api.sendNewVocaList(data)

            withContext(Dispatchers.Main) {
                //_vocaList.value = response
            }
            //Log.d("aaaa",response.toString())
        }
    }
    fun openDialog(context:Context,binding: NewVocaListBinding){
        val dialog=Dialog(context)
        dialog.setContentView(R.layout.new_voca_list)
        dialog.show()
        dialog.8
        binding.newCancel.setOnClickListener {
            dialog.dismiss()
            Log.d("aaaa","시발 왜 ")
        }
        binding.newSave.setOnClickListener {
            val data:MyDataTypes.NewVocaList=MyDataTypes.NewVocaList(binding.inputOwnerName.text.toString(),binding.inputListName.text.toString(),binding.inputListPassword.text.toString())
            postVocaListToServer(data)
        }
    }

}