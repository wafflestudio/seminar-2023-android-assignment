package com.jutak.assignment3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailVocaViewModel @Inject constructor(
    private val api: MyRestAPI
) : ViewModel() {

    private val _wordList = MutableLiveData<WordList>()
    val wordList:LiveData<WordList> = _wordList

    var password:String = ""

    private val _permission = MutableLiveData<Boolean>()
    val permission: LiveData<Boolean> = _permission

    init{
        _permission.value = false
    }
    suspend fun fetchWordList(id: Int){
        _wordList.value = api.getWordListById(id)
    }

    suspend fun checkPermission(id: Int, pass: Password){
        if(api.getPermission(pass, id).valid){
            password = pass.password
            _permission.value = true
        }
    }
}