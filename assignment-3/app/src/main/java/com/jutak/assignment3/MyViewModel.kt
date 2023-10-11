package com.jutak.assignment3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
): ViewModel() {

    @Inject
    lateinit var api: MyRestAPI

    private val _vocaList: MutableLiveData<List<Voca>> = MutableLiveData(listOf())
    val vocaList: LiveData<List<Voca>> = _vocaList

    fun loadVoca(){
        viewModelScope.launch(Dispatchers.IO) {
            val temp = api.getVocaListSuspend()
            withContext(Dispatchers.Main) {
                _vocaList.value = temp
            }
        }
    }

    /*fun getVocaList() : List<Voca>{
        return vocaList.value ?: emptyList()
    }*/
}