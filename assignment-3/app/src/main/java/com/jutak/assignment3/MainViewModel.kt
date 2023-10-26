package com.jutak.assignment3

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
class MainViewModel @Inject constructor(private val api: MyRestAPI)
    : ViewModel() {
    private val _wordLists : MutableLiveData<List<ExList>> = MutableLiveData()
    val wordLists : LiveData<List<ExList>> = _wordLists

    private val _listDetail : MutableLiveData<ListDetail> = MutableLiveData()
    val listDetail : LiveData<ListDetail> = _listDetail

    private val _wordList : MutableLiveData<List<Word>> = MutableLiveData()
    val wordList : LiveData<List<Word>> = _wordList

    // 최소 스펙
    fun fetchWordLists(){
        viewModelScope.launch(Dispatchers.IO){
            val wordLists = api.getWordLists()
            withContext(Dispatchers.Main){
                _wordLists.value = wordLists
            }
        }
    }

    fun createWordList(wordList: NewList){
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.postWordList(wordList)
            withContext(Dispatchers.Main){
                val currentData = _wordLists.value ?: mutableListOf()
                val newData = response.body() ?: emptyList()
                val combinedData = mutableListOf<ExList>()
                combinedData.addAll(currentData)
                combinedData.addAll(newData)
                _wordLists.value = combinedData
            }
        }
    }

    fun fetchListDetail(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            val listDetail = api.getWordList(id)
            withContext(Dispatchers.Main){
                _listDetail.value = listDetail
            }
        }
    }

    // 추가 스펙

}