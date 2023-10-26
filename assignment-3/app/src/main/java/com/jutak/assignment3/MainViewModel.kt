package com.jutak.assignment3

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.jutak.assignment3.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _wordListInfoData = MutableLiveData<MutableList<MyData.WordListInfo>>()
    val wordListInfoData: LiveData<MutableList<MyData.WordListInfo>> get() = _wordListInfoData
    private val _wordListData = MutableLiveData<MutableList<MyData.WordInfo>>()
    val wordListData: LiveData<MutableList<MyData.WordInfo>> get() = _wordListData
    // ViewModel에서 사용할 메서드 및 데이터를 정의

    private var wordListInfo = mutableListOf<MyData.WordListInfo>()
    private var wordInfos = mutableListOf<MyData.WordInfo>()

    fun getWordListInfo(): MutableList<MyData.WordListInfo> {
        fetchWordListInfo()
        return wordListInfo
    }

    fun getWordInfos(id: Int): MutableList<MyData.WordInfo> {
        fetchWordList(id)
        return wordInfos
    }

    fun fetchWordListInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            wordListInfo = repository.getWordListInfo()
            withContext(Dispatchers.Main) {
                _wordListInfoData.value = wordListInfo
                Log.d("MainViewModel", "WordListInfos Size: ${wordListInfo.size}") // 디버그 로그 추가
            }
        }
    }

    fun fetchWordList(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            wordInfos = repository.getWordList(id).word_list.toMutableList()
            Log.d("MainViewModel", "Word list size: ${wordInfos.size}") // 디버그 로그 추가
                withContext(Dispatchers.Main) {
                _wordListData.setValue(wordInfos)
            }
        }
    }

    fun pushWordList(data: MyData.WordListPostInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.pushWordList(data).toMutableList()
            withContext(Dispatchers.Main) {
                _wordListInfoData.value = response
                Log.d("MainViewModel", "WordListInfos Size: ${response.size}") // 디버그 로그 추가
            }
        }
    }
}