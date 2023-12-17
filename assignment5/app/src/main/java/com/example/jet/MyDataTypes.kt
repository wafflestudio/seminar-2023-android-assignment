package com.example.jet


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class MyDataTypes(){
    @JsonClass(generateAdapter = true)
    data class SearchResponse(
        @Json(name = "page") val page: Int,
        @Json(name = "results") val results: List<MovieInfo>,
        @Json(name = "total_pages") val totalPages: Int,
        @Json(name = "total_results") val totalResults: Int,
        )
    @JsonClass(generateAdapter = true)
    data class MovieInfo(
        @Json(name = "id") val id: Int,
        @Json(name = "adult") val adult: Boolean,
        @Json(name = "title") val title: String,
        @Json(name = "release_date") val releaseDate: String,
        @Json(name = "vote_average") val voteAverage: Double,
        @Json(name = "poster_path") val posterPath: String?,
    )


}