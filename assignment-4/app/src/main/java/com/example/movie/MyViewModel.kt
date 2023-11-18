package com.example.movie

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val api:MyRestAPI,
): ViewModel(){

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error

    private val _transition = MutableLiveData<Event<String>>()
    val transition: LiveData<Event<String>> = _transition


    //navController.navigate(R.id.loginFragment)

    private fun handleError(exception: Throwable) {
        val message = exception.message ?: ""
        _error.value = Event(message)
    }
    private fun handleTransition() {
        _transition.value = Event("드가자")
    }
    fun checkAPIKey(key:String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response=api.checkAPIKey("Bearer "+key)
                Log.d("aaaa",response.toString())
                withContext(Dispatchers.Main){
                    if(response.success){
                        handleTransition()
                    }
                }
            } catch (e: Exception) {
                //Toast.makeText(getActivity(), "Wrong Token", Toast.LENGTH_LONG).show()
                withContext(Dispatchers.Main){
                    handleError(e)
                }
            }

        }
    }

    fun printOne(){
        Log.d("aaaa","1")
    }
}
