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
    private val api: MyRestAPI,
    ): ViewModel() {

    var liveWordSetList: MutableLiveData<List<WordSet>> = MutableLiveData()
    val wordSetList = mutableListOf<WordSet>()
    var liveWordList: MutableLiveData<List<Word>> = MutableLiveData()
    val wordList = mutableListOf<Word>()

    val liveHasPermission: MutableLiveData<Boolean> = MutableLiveData(false)
    private var password: String = ""

    fun getWordSetListFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getWordSetList()
            withContext(Dispatchers.Main) {
                wordSetList.clear()
                for (wordSet in response) {
                    wordSetList.add(wordSet)
                }
                liveWordSetList.value = wordSetList
            }
        }
    }

    fun getWordSetFromServer(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getWordSet(id).wordList
            withContext(Dispatchers.Main) {
                wordList.clear()
                for (word in response) {
                    wordList.add(word)
                }
                liveWordList.value = wordList
            }
        }
    }

    fun postWordSetToServer(input: PostWordSetInput) {
        viewModelScope.launch(Dispatchers.IO) {
            if (input.name == "" || input.owner == "" || input.password == "") {
                return@launch
            }
            val response = api.postWordSet(input)
            withContext(Dispatchers.Main) {
                wordSetList.clear()
                for (wordSet in response) {
                    wordSetList.add(wordSet)
                }
                liveWordSetList.value = wordSetList
            }
        }
    }

    fun checkPermissionFromServer(pw: PasswordInput, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.hasPermission(pw, id)
            withContext(Dispatchers.Main) {
                if (response.valid) {
                    liveHasPermission.value = true
                    password = pw.password
                }
                else {
                    liveHasPermission.value = false
                }
            }
        }
    }

    fun putWordToServer(wordInput: Word, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (wordInput.spell == "" || wordInput.meaning == "") {
                return@launch
            }
            val response = api.putWord(PutWordInput(password, wordInput), id).wordList
            withContext(Dispatchers.Main) {
                wordList.clear()
                for (word in response) {
                    wordList.add(word)
                }
                liveWordList.value = wordList
            }
        }
    }

    fun deleteWordSetInServer(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            api.deleteWordSet(PasswordInput(password), id)
        }
    }

}