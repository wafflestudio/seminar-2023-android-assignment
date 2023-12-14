package com.example.movie

import retrofit2.http.GET
import retrofit2.http.Header

interface MyRestAPI {
    @GET("/3/authentication")
    suspend fun checkAPIKey(@Header("Authorization") token: String?):MyDataTypes.APIResponse

    @GET("/3/movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(@Header("Authorization") token: String?):MyDataTypes.MoviePageInfo
}