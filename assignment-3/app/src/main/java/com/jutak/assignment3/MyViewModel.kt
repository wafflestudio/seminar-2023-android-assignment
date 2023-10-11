package com.jutak.assignment3

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(): ViewModel() {
    var resbody=emptyList<MyModels.Wordlists>().toMutableList()
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
