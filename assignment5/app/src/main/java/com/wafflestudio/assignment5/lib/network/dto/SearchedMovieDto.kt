package com.wafflestudio.assignment5.lib.network.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchedMovieDto(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<MovieDetail>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
) : Parcelable