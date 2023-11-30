package com.wafflestudio.assignment5

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RestAPI {
    @GET("3/search/movie")
    suspend fun getmovies(
        @Header("Authorization") key:String="Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MmE3YWM3YmFkNzYxNmIxZThhMTc3ZjU4NmMwOWU5MyIsInN1YiI6IjY1NTM0OTBkOTY1M2Y2MTNmNDc0OWE0MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gdB1lLd0H_1Fd9FUCi88NQUsYXDKzIz141VtP3tcReo",
        @Query ("query") searchkey:String,
        @Query ("page") page:Int
    ):Movies
}