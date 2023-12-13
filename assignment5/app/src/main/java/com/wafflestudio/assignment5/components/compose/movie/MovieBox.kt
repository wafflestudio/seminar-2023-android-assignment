package com.wafflestudio.assignment5.components.compose.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.wafflestudio.assignment5.lib.network.dto.MovieDetail

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieBox(modifier: Modifier, movieDetail: MovieDetail) {
    Box {
        Row (modifier = Modifier.background(Color.Gray).fillMaxWidth()) {
            Column {
                GlideImage(
                    modifier = Modifier.size(150.dp),
                    contentDescription = "movie",
                    model = "https://image.tmdb.org/t/p/w500/${movieDetail.posterPath}",
                )
            }
            Column {
                Row {
                    Text(text = movieDetail.title)
                }
                Row {
                    Text(text = movieDetail.voteAverage.toString())
                    Text(text = movieDetail.releaseDate)
                }
            }
        }
    }
}