package com.jutak.assignment3

import android.content.Context
import android.util.Log
import android.widget.Toast
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

    private val _valid : MutableLiveData<Boolean> = MutableLiveData(false)
    val valid : LiveData<Boolean> = _valid

    private var _password : String = ""

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
    
    fun confirmPassword(context : Context, id : Int, password : String){
        viewModelScope.launch(Dispatchers.IO){
            val response = api.confirmPassword(id.toString(), Password(password))
            if(response.isSuccessful){
                withContext(Dispatchers.Main){
                    if(response.body()==Valid(true)){
                        _valid.value = true
                        _password = password
                    }
                    else Toast.makeText(context,"비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun deleteDialog(id : Int){
        viewModelScope.launch(Dispatchers.IO){
            val response = api.deleteList(id.toString(), Password(_password))
        }
    }

}