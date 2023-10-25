package com.jutak.assignment3

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
    private val api:MyRestAPI
): ViewModel() {
    var liveresbody:MutableLiveData<List<MyModels.Wordlists>> =MutableLiveData()
    var resbody = mutableListOf<MyModels.Wordlists>()
    var livewordlist:MutableLiveData<List<MyModels.Word>> = MutableLiveData()
    var wordlist= mutableListOf<MyModels.Word>()
    var livepermission:MutableLiveData<Boolean> =MutableLiveData(false)
    var curpermission:Boolean=false
    var curid:Int=0
    var curpw:String=""

    fun a(){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val response = api.word_lists_list()
                resbody.clear()
                response.forEach{
                    resbody.add(it)
                }
                liveresbody.value = resbody
            }
        }
    }

    fun b(id:Int,wlname:String){
        viewModelScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                val response=api.word_list_read(id).word_list
                wordlist.clear()
                response.forEach{
                    wordlist.add(it)
                }
                livewordlist.value=wordlist
            }
        }
    }

    fun postwordlist(data:MyModels.Data_newlist){
        viewModelScope.launch (Dispatchers.IO){
            withContext(Dispatchers.Main) {
                val response = api.word_list_create(data)
                resbody.add(response.last())
                liveresbody.value = resbody
            }
        }
    }

    fun pwcorrect(pw:String){
        viewModelScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                val response=api.word_list_permission(MyModels.Datapw(pw),curid)
                when (response.valid){
                    true->{
                        curpw=pw
                        curpermission=true
                        livepermission.value=curpermission
                    }
                    else->{
                        // TODO:
                    }
                }
            }
        }
    }

    fun deletewordlist(){
        viewModelScope.launch (Dispatchers.IO){
            withContext(Dispatchers.Main){
                val response=api.word_list_delete(MyModels.Datapw(curpw),curid)
            }
        }
    }
}
