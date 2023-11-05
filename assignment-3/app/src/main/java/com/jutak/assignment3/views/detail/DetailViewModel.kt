package com.jutak.assignment3.views.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jutak.assignment3.data.model.Word
import com.jutak.assignment3.data.model.WordList
import com.jutak.assignment3.data.repository.WordListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val wordListRepository: WordListRepository,
) : ViewModel() {

    private val _editMode: MutableLiveData<Boolean> = MutableLiveData(false)
    val editMode: LiveData<Boolean> = _editMode

    var password: String = ""

    suspend fun addNewWordToWordList(listId: Int, password: String, word: Word): WordList {
        return wordListRepository.addWordToWordList(listId, password, word)
    }

    suspend fun deleteWordList(listId: Int, password: String) {
        wordListRepository.deleteRemoteWordList(listId, password)
    }

    suspend fun checkWordListEditPermission(listId: Int, password: String) {
        wordListRepository.checkWordListEditPermission(listId, password).let {
            _editMode.postValue(it)
            if (it) {
                this.password = password
            }
        }
    }
}