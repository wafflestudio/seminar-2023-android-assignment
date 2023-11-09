package com.wafflestudio.assignment4

import com.squareup.moshi.Json

data class DataMovie(
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "backdrop_path") val backdrop_path: String,
    @Json(name = "genre_ids") val genre_ids: Array<Integer>,
    @Json(name = "id") val id: Integer,
    @Json(name = "original_language") val original_language: String,
    @Json(name = "original_title") val original_title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity: Number,
    @Json(name = "poster_path") val poster_path: String,
    @Json(name = "release_date") val release_data: String,
    @Json(name = "title") val title: String,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") val vote_average: Number,
    @Json(name = "vote_count") val vote_count: Integer,
) {
}