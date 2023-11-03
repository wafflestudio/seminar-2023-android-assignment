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
    private val restApi: MyRestAPI,
): ViewModel() {

    val liveWordSets: MutableLiveData<List<VocabularySet>> = MutableLiveData()
    val liveVocabularyDetails: MutableLiveData<VocabularySetDetails> = MutableLiveData()
    private val _words = MutableLiveData<List<Word>>()  // Internal MutableLiveData
    val words: LiveData<List<Word>> = _words



    fun fetchVocabularySets() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val wordSetsFromServer = restApi.fetchAllVocabularySets()
                withContext(Dispatchers.Main) {
                    liveWordSets.value = wordSetsFromServer
                }
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }



    fun fetchVocabularySetDetails(setId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val vocabularyDetails = restApi.fetchVocabularySetDetails(setId)
                withContext(Dispatchers.Main) {
                    liveVocabularyDetails.value = vocabularyDetails
                }
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

    fun postWordSetToServer(postInput: PostWordSetInput) {
        viewModelScope.launch {
            try {
                val response = restApi.postWordSet(postInput)
                if (response != null) {
                    val updatedList = liveWordSets.value?.toMutableList()
                    updatedList?.add(response)
                    liveWordSets.postValue(updatedList)
                }
            } catch (e: Exception) {
                // 에러 처리 로직 (예: Toast 메시지 표시)
            }
        }
    }

    fun addWordToWordSet(updatedVocabulary: Word, setId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val updatedDetails = restApi.updatedVocabulary(updatedVocabulary, setId)
                withContext(Dispatchers.Main) {
                    liveVocabularyDetails.value = updatedDetails
                }
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }
/*
    fun deleteVocabularySet(setId: Int, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                restApi.deleteVocabularySet(PasswordValidation(password), setId)
                fetchVocabularySets() // Optionally refresh the vocabulary sets list
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }


 */
    data class UpdateVocabulary(
        val wordSet: WordSet,
        val password: String
    )

    data class PasswordValidation(
        val password: String
    )

    data class PostWordSetInput(
        val setName: String,
        val ownerName: String,
        val password: String
    )

}
