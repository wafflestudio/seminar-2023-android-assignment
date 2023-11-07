package com.jutak.assignment3

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jutak.assignment3.databinding.AddDialogLayoutBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: MyRestAPI
): ViewModel() {
    private val _wordListsLiveData = MutableLiveData<MutableList<MyMultiData.WordListsInfo>>(mutableListOf())
    val wordListsLiveData: LiveData<MutableList<MyMultiData.WordListsInfo>> = _wordListsLiveData
    private val _wordsLiveData = MutableLiveData<MyMultiData.WordInfo>()
    val wordsLiveData: LiveData<MyMultiData.WordInfo> = _wordsLiveData

    suspend fun getWords(id: Int): MyMultiData.WordInfo {
        return api.getWordsSuspend(id)
    }
    fun getWordListsInfo(){
        viewModelScope.launch(Dispatchers.IO){
            val response = api.getWordListsInfoSuspend()
            withContext(Dispatchers.Main) {
                _wordListsLiveData.value = response
            }
        }
    }
    fun showAlertDialog(context: Context) {
        val alertDialog = AlertDialog.Builder(context)
        val binding = AddDialogLayoutBinding.inflate(LayoutInflater.from(context))
        alertDialog.setTitle("제목")
        alertDialog.setView(binding.root)

        alertDialog.setPositiveButton("확인") { dialog, which ->
            addWordList(binding.name.text.toString(),
                binding.owner.text.toString(),binding.pw.text.toString())
        }
        alertDialog.setNegativeButton("취소") {dialog, which -> dialog.dismiss()}
        alertDialog.show()
    }
    private fun addWordList(name:String, owner:String, password:String){
        viewModelScope.launch(Dispatchers.IO){
            val response = api.postWordListSuspend(MyMultiData.WordListPost(name,owner,password))
            withContext(Dispatchers.Main) {
                    val tempData = _wordListsLiveData.value ?: mutableListOf()
                    tempData.addAll(response)
                    _wordListsLiveData.value = tempData
            }

        }
    }





}