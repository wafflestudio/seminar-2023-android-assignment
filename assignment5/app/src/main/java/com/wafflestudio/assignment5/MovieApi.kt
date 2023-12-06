package com.wafflestudio.assignment5

import retrofit2.http.GET

interface MovieApi {
    @GET("/3/search/movie")
    suspend fun getMovie(): ResponseData
}