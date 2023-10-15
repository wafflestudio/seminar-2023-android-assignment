package com.jutak.assignment3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jutak.assignment3.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.jutak.assignment3.model.PermissionRequest
import com.jutak.assignment3.model.WordListBrief
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WordListDetailViewModel @Inject constructor(
    private val api: WordApi,
) : ViewModel() {

    var wordListBrief: WordListBrief? = null

    private var password: String? = null

    private val _words: MutableLiveData<List<Word>> = MutableLiveData(emptyList())

    val words: LiveData<List<Word>> = _words

    private val _permission: MutableLiveData<Boolean> = MutableLiveData(false)

    val permission: LiveData<Boolean> = _permission


    fun fetchWord(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = api.getWords(id)
            withContext(Dispatchers.Main) {
                wordListBrief = WordListBrief(id = resp.id, name = resp.name, owner = resp.owner)
                _words.value = resp.wordList
            }
        }
    }

    fun checkPermission(inputPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = api.checkPermission(wordListBrief!!.id, PermissionRequest(inputPassword))
            password = inputPassword
            withContext(Dispatchers.Main) {
                _permission.value = resp.valid
            }
        }
    }

}