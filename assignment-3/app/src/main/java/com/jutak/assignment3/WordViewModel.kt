package com.jutak.assignment3

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val api: MyRestAPI
): ViewModel() {

}