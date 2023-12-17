package com.example.jet

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
class MyViewModel @Inject constructor(
    private val api:MyRestAPI,
): ViewModel(){

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error

   val myMovieList : MutableState<List<MyDataTypes.MovieInfo>> = mutableStateOf(listOf())






    var page =1
    /*
    private fun handleError(exception: Throwable) {
        val message = exception.message ?: ""
        _error.value = Event(message)
    }*/
    fun getMoviesByQuery(query:String,page:Int){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response=api.getMoviesByQuery(query,false,page)
                Log.d("aaaa",response.toString())

                withContext(Dispatchers.Main){
                    myMovieList.value=response.results
                }
            } catch (e: Exception) {
                Log.d("aaaa","faikl")
                //Toast.makeText(getActivity(), "Wrong Token", Toast.LENGTH_LONG).show()
                withContext(Dispatchers.Main){
                    //handleError(e)
                }
            }

        }
    }
}