package com.wafflestudio.assignment4

import retrofit2.http.GET
import retrofit2.http.Header

interface MyRestApi {
    @GET("3/authentication")
    suspend fun fetchAuthenticationToken(@Header("Authorization") authorizationKey: String): AuthResult

    @GET("3/movie/popular")
    suspend fun fetchPopularMovies(@Header("Authorization") authorizationKey: String): MovieList
}
