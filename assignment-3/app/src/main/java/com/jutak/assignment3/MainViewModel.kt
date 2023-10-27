package com.jutak.assignment3

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

    private val wordLiveData: MutableLiveData<List<WordList>> = MutableLiveData()
    fun fetchWord(){
        viewModelScope.launch(Dispatchers.IO) {
            val wordList = api.getWordList()
            withContext(Dispatchers.Main){
                wordLiveData.value = wordList
            }
        }
    }

    fun returnData() :  MutableLiveData<List<WordList>>{
        return wordLiveData
    }


}