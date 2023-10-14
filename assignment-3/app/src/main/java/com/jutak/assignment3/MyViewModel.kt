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

    private val _wordList: MutableLiveData<List<MyMultiData.Word>> = MutableLiveData(listOf())
    val wordList: LiveData<List<MyMultiData.Word>> = _wordList

    fun loadVoca(){
        viewModelScope.launch(Dispatchers.IO) {
            val temp = api.getVocaListSuspend()
            withContext(Dispatchers.Main) {
                _vocaList.value = temp
            }
        }
    }

    fun loadVocaInfo(id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val temp = api.getWordListSuspend(id)
            withContext(Dispatchers.Main) {
                _wordList.value = temp.word_list
            }
        }
    }

    fun updateVoca(name:String, owner:String, pw:String) {
        val vocaAdd = MyMultiData.VocaAdd(name, owner, pw)
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.addVoca(vocaAdd)
            withContext(Dispatchers.Main) {
                if(response.body()!=null) _vocaList.value = response.body()!!
            }
        }
    }
}