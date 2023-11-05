package com.jutak.assignment3.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jutak.assignment3.data.model.SimpleWordList
import com.jutak.assignment3.data.model.WordList
import com.jutak.assignment3.data.repository.WordListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val wordListRepository: WordListRepository,
) : ViewModel() {

    private val _remoteList: MutableLiveData<List<SimpleWordList>> = MutableLiveData(listOf())
    val remoteList: LiveData<List<SimpleWordList>> = _remoteList

    suspend fun fetchRemoteWordLists() {
        val lists = wordListRepository.getRemoteWordLists()
        _remoteList.postValue(lists)
    }

    suspend fun postNewWordList(name: String, owner: String, password: String) {
        val lists = wordListRepository.postNewWordList(name, owner, password)
        _remoteList.postValue(lists)
    }

    suspend fun fetchWordListDetail(id: Int): WordList {
        return wordListRepository.getRemoteWordListById(id)
    }
}