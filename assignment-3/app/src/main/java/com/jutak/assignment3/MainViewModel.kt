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
): ViewModel() {
    private val _wordListsInfo = MutableLiveData<List<WordListsInfo>>(listOf())
    val wordListsInfo: LiveData<List<WordListsInfo>> = _wordListsInfo

    fun getWordListsInfo(){
        viewModelScope.launch(Dispatchers.IO){
            val response = api.getWordListsInfoSuspend()
            withContext(Dispatchers.Main) {
                _wordListsInfo.value = response
            }
        }
    }
}