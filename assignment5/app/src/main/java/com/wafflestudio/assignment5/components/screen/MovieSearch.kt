package com.wafflestudio.assignment5.components.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import com.wafflestudio.assignment5.components.compose.movie.SearchBar
import com.wafflestudio.assignment5.components.compose.movie.MovieContents


@Composable
fun MovieSearch(modifier: Modifier) {

    Column {
        SearchBar(modifier = Modifier)
        MovieContents(modifier = Modifier)
    }
    
}

