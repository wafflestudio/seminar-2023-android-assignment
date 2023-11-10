package com.example.voca

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.LocusId
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

    private val _inVocaList: MutableLiveData<MyDataTypes.VocaListSpecificInfo> = MutableLiveData()
    val inVocaList: LiveData<MyDataTypes.VocaListSpecificInfo> = _inVocaList

    private val _inVocaList_Voca: MutableLiveData<List<MyDataTypes.Voca>> = MutableLiveData(listOf())
    val inVocaList_Voca: LiveData<List<MyDataTypes.Voca>> = _inVocaList_Voca



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
            api.sendNewVocaList(data)


            withContext(Dispatchers.Main) {
                getVocaListFromServer()
                Log.d("aaaa","try")
            }
            //
        }
    }
    fun getVocaListSpecificInfoFromServer(id: Int){
        Log.d("aaaa","실행이 되기는 했음")
        //val rp:MyDataTypes.VocaListSpecificInfo

        viewModelScope.launch(Dispatchers.IO){
            Log.d("aaaa","실행이 되기는 했음2")
            var response:MyDataTypes.VocaListSpecificInfo

            response= api.getVocaSpecificInfo(id)



            withContext(Dispatchers.Main) {

                _inVocaList.value = response
                Log.d("aaaa","실행이 되기는 했음3")

            }
            Log.d("aaaa",response.toString())
            //Log.d("aaaa",response.toString())

        }


    }




}