package com.wafflestudio.assignment4

import retrofit2.http.GET
import retrofit2.http.Header

interface RestAPI {
    @GET("3/authentication")
    suspend fun authentication(@Header("Authorization") key:String):Authentication

    @GET("3/movie/popular")
    suspend fun getmovies(@Header("Authorization") key:String):Movie
}