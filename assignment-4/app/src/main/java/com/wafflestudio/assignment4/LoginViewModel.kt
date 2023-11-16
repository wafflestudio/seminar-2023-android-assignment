package com.wafflestudio.assignment4

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: RestAPI
):ViewModel() {
    var movielist:MutableList<MovieInfo> = mutableListOf()
    var livemoviellist: MutableLiveData<MutableList<MovieInfo>> = MutableLiveData()
    suspend fun login(key:String?):Boolean{
        if(key==null) return false
        val answer=api.authentication("Bearer "+key)
        return answer.success
    }

    suspend fun getmovies(){
        Log.d("aaaa","did")
        movielist=api.getmovies("Bearer "+MyApplication.prefs.getString("token","")).result.slice(0..<5).toMutableList()
        livemoviellist.value=movielist
        Log.d("aaaa",movielist.toString())
    }
}
