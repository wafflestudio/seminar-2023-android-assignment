package com.wafflestudio.assignment4

import com.squareup.moshi.Json

data class DataMovieAnswer (
    @Json(name = "page") val page: Integer,
    @Json(name = "results") val results: List<DataMovie>,
    @Json(name = "total_pages") val total_pages: Integer,
    @Json(name = "total_results") val total_results: Integer,
){
}