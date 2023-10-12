package com.jutak.assignment3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jutak.assignment3.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
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

    private val _words: MutableLiveData<List<Word>> = MutableLiveData(emptyList())

    val words: LiveData<List<Word>> = _words

    fun fetchWord(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = api.getWords(id)
            withContext(Dispatchers.Main) {
                wordListBrief = WordListBrief(id = resp.id, name = resp.name, owner = resp.owner)
                _words.value = resp.wordList
            }
        }
    }

}