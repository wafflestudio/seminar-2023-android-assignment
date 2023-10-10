package com.jutak.assignment3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(): ViewModel() {
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
