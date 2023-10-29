package com.jutak.assignment3

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.util.Log
import android.widget.Toast
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
    private val _permission = MutableLiveData<Boolean>()
    val permission: LiveData<Boolean> get() = _permission

    private var wordListInfos = mutableListOf<MyData.WordListInfo>()
    private var wordInfos = mutableListOf<MyData.WordInfo>()
    private var permissionData = false

    suspend fun getWordListInfo(): MutableList<MyData.WordListInfo> {
        fetchWordListInfo()
        return wordListInfos
    }

    suspend fun getWordList(id: Int): MutableList<MyData.WordInfo> {
        fetchWordList(id)
        return wordInfos
    }

    suspend fun getPermission(id: Int, password: String): Boolean {
        pushPassword(id, password)
        return permissionData
    }


    suspend fun fetchWordListInfo(): String {
        val fetchedList: Pair<MainRepository.Signs, Any?> = repository.getWordListInfo()
        val sign = fetchedList.first
        val responseValue = fetchedList.second
        when (sign) {
            MainRepository.Signs.SUCCESS ->
                withContext(Dispatchers.Main) {
                    wordListInfos = responseValue as MutableList<MyData.WordListInfo>
                    _wordListInfoData.value = wordListInfos
                }
            MainRepository.Signs.FAILURE -> return responseValue as String
            MainRepository.Signs.EXCEPTION -> return responseValue as String
        }

        return "SUCCESS"
    }

    // suspend fun이 아닐 경우, 다 받아오기 전에 함수가 종료될 수 있음
    // suspend fun을 쓰고, viewModelScope...를 없애는 경우: Activity에서도 코루틴을...
    suspend fun fetchWordList(id: Int): String {
        val fetchedList: Pair<MainRepository.Signs, Any?> = repository.getWordList(id)
        val sign = fetchedList.first
        val responseValue = fetchedList.second
        when (sign) {
            MainRepository.Signs.SUCCESS ->
                withContext(Dispatchers.Main) {
                    val wordList = responseValue as MyData.WordList
                    wordInfos = wordList.word_list.toMutableList()
                    _wordListData.value = wordInfos
                }
            MainRepository.Signs.FAILURE -> return responseValue as String
            MainRepository.Signs.EXCEPTION -> return responseValue as String
        }

        return "SUCCESS"
    }

    suspend fun pushWordList(data: MyData.WordListPostInfo): String {
        val fetchedList: Pair<MainRepository.Signs, Any?> = repository.pushWordList(data)
        val sign = fetchedList.first
        val responseValue = fetchedList.second
        when (sign) {
            MainRepository.Signs.SUCCESS ->
                withContext(Dispatchers.Main) {
                    _wordListInfoData.value = responseValue as MutableList<MyData.WordListInfo>
                }
            MainRepository.Signs.FAILURE -> return responseValue as String
            MainRepository.Signs.EXCEPTION -> return responseValue as String
        }



        return "SUCCESS"
    }

    suspend fun pushPassword(id: Int, password: String): String {
        val fetchedList: Pair<MainRepository.Signs, Any?> = repository.verifyPassword(id, password)
        val sign = fetchedList.first
        val responseValue = fetchedList.second

        when (sign) {
            MainRepository.Signs.SUCCESS ->
                withContext(Dispatchers.Main) {
                    val response = responseValue as MyData.Validation
                    permissionData = response.valid
                    _permission.value = permissionData
                }
            MainRepository.Signs.FAILURE -> return responseValue as String
            MainRepository.Signs.EXCEPTION -> return responseValue as String
        }

        return "SUCCESS"
    }

    suspend fun deleteWordList(id: Int, password: String): String {
        if (!(_permission.value ?: false)) {
            return "Wrong Access."
        }
        val fetchedList: Pair<MainRepository.Signs, Any?> = repository.deleteWordList(id, password)
        val sign = fetchedList.first
        val responseValue = fetchedList.second

        when (sign) {
            MainRepository.Signs.SUCCESS -> fetchWordListInfo()
            MainRepository.Signs.FAILURE -> return responseValue as String
            MainRepository.Signs.EXCEPTION -> return responseValue as String
        }

        return "SUCCESS"
    }

    suspend fun putWord(id: Int, word: MyData.WordPutInfo): String {
        if (!(_permission.value?: false)) {
            return "Wrong Access."
        }

        val fetchedList: Pair<MainRepository.Signs, Any?> = repository.putWord(id, word)
        val sign = fetchedList.first
        val responseValue = fetchedList.second
        Log.d("VM", "putWord On")

        when (sign) {
            MainRepository.Signs.SUCCESS -> fetchWordList(id)
            MainRepository.Signs.FAILURE -> return responseValue as String
            MainRepository.Signs.EXCEPTION -> return responseValue as String
        }

        return "SUCCESS"
    }
}