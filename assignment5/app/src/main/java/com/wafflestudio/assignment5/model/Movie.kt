package com.wafflestudio.assignment5.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "id") val id: Int,
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "title") val title: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "poster_path") val posterPath: String?,
) : Parcelable
