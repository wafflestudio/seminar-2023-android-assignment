package com.wafflestudio.assignment4

import retrofit2.http.Header
import retrofit2.http.GET
import retrofit2.http.Headers

interface MyRestAPI {
    @Headers("Accept: application/json")
    @GET("/3/movie/popular")
    suspend fun fetchPopularMovies(@Header("Authorization")authorization: String): MovieData

    @Headers("Accept: application/json")
    @GET("/3/authentication")
    suspend fun authenticateUser(@Header("Authorization")authorization: String): LoginData
}