package com.wafflestudio.assignment4

import com.squareup.moshi.Json

data class MovieDataResponse (
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<MovieData>,
    @Json(name = "total_pages") val total_pages: Int,
    @Json(name = "total_results") val total_results: Int,
){
}