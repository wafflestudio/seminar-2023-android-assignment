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
class MainViewModel @Inject constructor(
    private val api: MyRestAPI
) : ViewModel() {

    private val _wordBookList = MutableLiveData<List<WordBook>>()
    val wordBookList: LiveData<List<WordBook>> = _wordBookList

    suspend fun fetchWordBooks(){
        val wordBookList = api.getWordBookList()
        _wordBookList.value = wordBookList
    }

    suspend fun addWordBook(owner:String, name:String, pass:String){
        val createWordBook = CreateWordBook(name, owner, pass)
        val response = api.wordBookCreate(createWordBook)
        _wordBookList.value = response.body()
    }

    suspend fun fetchWordList(id: Int): WordList{
        return api.getWordListById(id)
    }

}