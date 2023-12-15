package com.example.jet

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

    private val _myMovieList : MutableLiveData<List<MyDataTypes.MovieInfo>> = MutableLiveData(listOf())
    val myMovieList: LiveData<List<MyDataTypes.MovieInfo>> = _myMovieList

    private fun handleError(exception: Throwable) {
        val message = exception.message ?: ""
        _error.value = Event(message)
    }
    fun getMoviesByQuery(query:String,page:Int){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response=api.getMoviesByQuery(query,page)
                //Log.d("aaaa",response.toString())

                withContext(Dispatchers.Main){
                    _myMovieList.value=response.results.subList(0,5)
                }
            } catch (e: Exception) {
                //Toast.makeText(getActivity(), "Wrong Token", Toast.LENGTH_LONG).show()
                withContext(Dispatchers.Main){
                    handleError(e)
                }
            }

        }
    }
}