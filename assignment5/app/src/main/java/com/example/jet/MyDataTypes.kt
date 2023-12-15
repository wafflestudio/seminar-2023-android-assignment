package com.example.jet


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class MyDataTypes(){
    @JsonClass(generateAdapter = true)
    data class SearchResponse(
        @Json(name = "page") val id: Int,
        @Json(name = "results") val results: List<MovieInfo>,
        @Json(name = "total_pages") val totalPages: Int,
        @Json(name = "total_results") val totalResults: Int,
        )
    @JsonClass(generateAdapter = true)
    data class MovieInfo(
        @Json(name="adult")val adult:Boolean,
        @Json(name="backdrop_path")val backdropPath:String,
        @Json(name="genre_ids")val genreIds:List<Int>,
        @Json(name="id")val id:Int,
        @Json(name="original_language")val originalLanguage:String,
        @Json(name="original_title")val originalTitle:String,
        @Json(name="overview")val overview:String,
        @Json(name="popularity")val popularity:Float,
        @Json(name="poster_path")val posterPath:String,
        @Json(name="release_date")val releaseDate: String,
        @Json(name="title")val title:String,
        @Json(name="video")val video:Boolean,
        @Json(name="vote_average")val voteAverage:Float,
        @Json(name="vote_count")val voteCount:Int,
    )


}