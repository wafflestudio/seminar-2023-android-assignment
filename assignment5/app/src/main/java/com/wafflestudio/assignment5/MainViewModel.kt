package com.wafflestudio.assignment5

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val api: MovieApi
):ViewModel() {
    companion object {}

    private val _movies: MutableStateFlow<List<MovieData>> = MutableStateFlow(listOf())
    val movies = _movies.asStateFlow()

}

