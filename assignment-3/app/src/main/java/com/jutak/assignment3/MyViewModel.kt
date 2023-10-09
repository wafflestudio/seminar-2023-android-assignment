package com.jutak.assignment3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel: ViewModel() {
    /*
    fun fetchPerson(){
        viewModelScope.launch(Dispatchers.IO){
            val person=api.getPersonSuspend()
            withContext(Dispatchers.Main){
                _data.value=person
            }
        }
    }
    */
}