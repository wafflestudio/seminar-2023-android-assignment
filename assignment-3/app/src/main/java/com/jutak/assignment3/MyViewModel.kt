package com.jutak.assignment3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
): ViewModel() {

    @Inject
    lateinit var api: MyRestAPI

    private val _vocaList: MutableLiveData<List<MyMultiData.Voca>> = MutableLiveData(listOf())
    val vocaList: LiveData<List<MyMultiData.Voca>> = _vocaList

    fun loadVoca(){
        viewModelScope.launch(Dispatchers.IO) {
            val temp = api.getVocaListSuspend().toMutableList()
            withContext(Dispatchers.Main) {
                _vocaList.value = temp
            }
        }
    }

    fun updateVoca(owner:String, name:String, pw:String) {
        val vocaAdd = MyMultiData.VocaAdd(name, owner, pw)
        Log.d("update voca", vocaAdd.name)
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.addVoca(vocaAdd)
            Log.d("get response", "passed")
            withContext(Dispatchers.Main) {
                Log.d("final", _vocaList.value.toString())
                _vocaList.value = response
            }
        }
    }
}