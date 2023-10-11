package com.jutak.assignment3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jutak.assignment3.model.CreateWordListRequest
import com.jutak.assignment3.model.WordListBrief
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: WordApi,
) : ViewModel() {

    init {
        fetchWordLists()
    }

    private val _wordLists: MutableLiveData<List<WordListBrief>> = MutableLiveData(emptyList())

    val wordLists: LiveData<List<WordListBrief>> = _wordLists

    fun fetchWordLists() {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = api.getWordLists()
            withContext(Dispatchers.Main) {
                _wordLists.value = resp
            }
        }
    }

    fun createWordList(ownerName: String, wordListName: String, password: String) {
        val request = CreateWordListRequest(name = wordListName, owner = ownerName, password = password)
        viewModelScope.launch(Dispatchers.IO) {
            val resp = api.createWordList(request)
            withContext(Dispatchers.Main) {
                _wordLists.value = resp
            }
        }
    }

}