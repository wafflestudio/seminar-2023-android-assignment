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
class WordViewModel @Inject constructor(
    private val api: MyRestAPI
) : ViewModel(){
    private val _words : MutableLiveData<WordListDetail> = MutableLiveData()
    val words : LiveData<WordListDetail> = _words

    fun fetchWords(id : Int){
        viewModelScope.launch(Dispatchers.IO){
            val response = api.getWords(id.toString())
            if(response.isSuccessful){
                withContext(Dispatchers.Main){
                    _words.value = response.body()
                }
            }
        }
    }
}