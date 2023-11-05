package com.jutak.assignment3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailVocaViewModel @Inject constructor(
    private val api: MyRestAPI
) : ViewModel() {

    private val _wordList = MutableLiveData<WordList>()
    val wordList:LiveData<WordList> = _wordList


}