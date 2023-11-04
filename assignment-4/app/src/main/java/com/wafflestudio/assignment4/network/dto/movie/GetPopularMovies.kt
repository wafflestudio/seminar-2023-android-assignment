package com.wafflestudio.assignment4.network.dto.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetPopularMovies(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val movies: List<Movie>,
)
