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
) : ViewModel() {

    private val _wordListsLiveData = MutableLiveData<MutableList<MyMultiData.WordListsInfo>>(mutableListOf())
    val wordListsLiveData: LiveData<MutableList<MyMultiData.WordListsInfo>> = _wordListsLiveData

    fun getWordListsInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getWordListsInfoSuspend()
                postWordLists(response)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching word lists", e)
            }
        }
    }

    private fun postWordLists(wordLists: MutableList<MyMultiData.WordListsInfo>) {
        _wordListsLiveData.postValue(wordLists)
    }

    fun showAlertDialog(context: Context) {
        val binding = AddDialogLayoutBinding.inflate(LayoutInflater.from(context))
        AlertDialog.Builder(context).apply {
            setTitle("제목")
            setView(binding.root)
            setPositiveButton("확인") { _, _ ->
                addWordList(binding.name.text.toString(), binding.owner.text.toString(), binding.pw.text.toString())
            }
            setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
            show()
        }
    }

    private fun addWordList(name: String, owner: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.postWordListSuspend(MyMultiData.WordListPost(name, owner, password))
                withContext(Dispatchers.Main) {
                    val tempData = _wordListsLiveData.value ?: mutableListOf()
                    tempData.addAll(response)
                    _wordListsLiveData.value = tempData
                }
            } catch (e: retrofit2.HttpException) {
                Log.d("okay", e.response()?.errorBody()?.string().toString())
            }
        }
    }


    suspend fun getWords(id: Int): MyMultiData.WordInfo {
        return withContext(Dispatchers.IO) {
            api.getWordsSuspend(id)
        }
    }
}
