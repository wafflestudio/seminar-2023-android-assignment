package com.wafflestudio.assignment4

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResult(
    @Json(name = "success") val isSuccess: Boolean,
    @Json(name = "status_code") val statusCode: Int,
    @Json(name = "status_message") val statusMessage: String
)

@JsonClass(generateAdapter = true)
data class MovieList(
    @Json(name = "page") val pageNumber: Int,
    @Json(name = "results") val movieDetails: List<MovieDetail>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class MovieDetail(
    @Json(name = "adult") val isAdult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name = "genre_ids") val genreIds: Array<Int>,
    @Json(name = "id") val movieId: Int,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "overview") val overviewDescription: String,
    @Json(name = "popularity") val popularityScore: Float,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "title") val movieTitle: String,
    @Json(name = "video") val hasVideo: Boolean,
    @Json(name = "vote_average") val averageVote: Float,
    @Json(name = "vote_count") val voteCount: Int
)
