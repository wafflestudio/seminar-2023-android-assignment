package com.wafflestudio.assignment5

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: RestAPI
): ViewModel() {
    //var movielist:List<MovieInfo> = listOf()
    //var _movielist: MutableStateFlow<List<MovieInfo>> = MutableStateFlow(listOf())
    //var livemoviellist: MutableLiveData<MutableList<MovieInfo>> = MutableLiveData()
    private val _movielist:MutableStateFlow<Movies> = MutableStateFlow(
        Movies(0, listOf(),0,0)
    )
    val movielist = _movielist.asStateFlow()
    suspend fun getmovies(skey:String,page:Int){
        //Log.d("aaaa",movielist.toString())
        _movielist.emit(api.getmovies(searchkey = skey, page=page))
        //livemoviellist.value=movielist
    }
}