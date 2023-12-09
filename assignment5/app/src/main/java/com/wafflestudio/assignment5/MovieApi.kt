package com.wafflestudio.assignment5

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/search/movie")
    suspend fun getMovie(@Query("query") query: String): ResponseData
}