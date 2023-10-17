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

    private val _wordLists : MutableLiveData<List<WordListRead>> = MutableLiveData(emptyList())
    val wordLists : LiveData<List<WordListRead>> = _wordLists

    fun fetchWordLists() {
        viewModelScope.launch(Dispatchers.IO) {
            val newWordLists = api.getWordLists()
            withContext(Dispatchers.Main) {
                _wordLists.value = newWordLists
            }
        }
    }

    fun createWordList(owner : String, name : String, password : String){
        viewModelScope.launch(Dispatchers.IO){
            val newWordList = WordListWrite(owner = owner,name = name,password = password)
            val response = api.createWordList(newWordList)
            if(response.isSuccessful){
                withContext(Dispatchers.Main) {
                    _wordLists.value = response.body()
                }
            }
        }
    }

}