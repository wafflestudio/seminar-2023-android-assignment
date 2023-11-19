package com.wafflestudio.assignment4

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface MovieApi {
    @GET("/3/authentication")
    suspend fun getLogin(@Header("Authorization") authorization: String): LoginSuccess

    @GET("/3/movie/popular")
    suspend fun getMovie(@Header("Authorization") authorization: String): MovieList


}