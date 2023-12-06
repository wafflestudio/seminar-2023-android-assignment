package com.wafflestudio.assignment5

import com.squareup.moshi.Json

data class MovieData(
    @Json(name = "title") val title: String,
    @Json(name = "release_date") val date: String,
    @Json(name = "vote_average") val vote: String,
    @Json(name = "poster_path") val path: String,
)
