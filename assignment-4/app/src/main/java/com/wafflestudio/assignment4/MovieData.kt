package com.wafflestudio.assignment4

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieData (
    @Json(name = "overview") val overview: String,
    @Json(name = "poster_path") val poster_path: String,
    @Json(name = "release_date") val release_date: String,
    @Json(name = "title") val title: String,
    @Json(name = "vote_average") val vote_average: Float,
)