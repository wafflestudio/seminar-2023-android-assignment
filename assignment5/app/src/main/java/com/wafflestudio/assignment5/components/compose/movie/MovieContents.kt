package com.wafflestudio.assignment5.components.compose.movie

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.wafflestudio.assignment5.MovieViewModel
import com.wafflestudio.assignment5.lib.network.dto.MovieDetail
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun MovieContents(modifier: Modifier) {
    val movieViewModel: MovieViewModel = hiltViewModel()
    val movieDetails by movieViewModel.movieDetails.collectAsState()

    LazyColumn (modifier = Modifier.fillMaxWidth()) {
        items(movieDetails.size) { position ->
            MovieBox(modifier = Modifier,
                movieDetail = movieDetails[position])
        }
    }

}