package com.jutak.assignment3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val myApiService: MyRestAPI
) : ViewModel() {

    private val _word = MutableLiveData<Word>()
    val word: LiveData<Word> = _word

    private val _vocabularyList = MutableLiveData<List<MyMultiData.Voca>>()
    val vocabularyList: LiveData<List<MyMultiData.Voca>> get() = _vocabularyList

    private val _wordsList = MutableLiveData<List<MyMultiData.Word>>()
    val wordsList: LiveData<List<MyMultiData.Word>> get() = _wordsList

    private val _vocabularyInfo = MutableLiveData<MyMultiData.VocabularyInfo>()
    val vocabularyInfo: LiveData<MyMultiData.VocabularyInfo> get() = _vocabularyInfo

    var selectedWord: MyMultiData.Word? = null

    fun fetchVocabulary() {
        viewModelScope.launch(Dispatchers.IO) {
            val vocabularies = myApiService.getVocaListSuspend()
            withContext(Dispatchers.Main) {
                _vocabularyList.value = vocabularies
            }
        }
    }


    fun fetchVocabularyInfo(vocabularyId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            vocabularyId?.toIntOrNull()?.let { vocabId ->
                val words = myApiService.getWordListSuspend(vocabId.toString())
                val vocabularyInfo = myApiService.getVocaInfoSuspend(vocabId)
                withContext(Dispatchers.Main) {
                    _wordsList.value = listOf(words)
                    _vocabularyInfo.value = vocabularyInfo
                }
            }
        }
    }



    fun fetchWordDetails(wordPosition: Int) {
        _wordsList.value?.get(wordPosition)?.let {
            selectedWord = it
        }
    }
}
data class Word(
    val spell: String,
    val meaning: String,
    val synonym: String?,
    val antonym: String?,
    val example: String?
)

