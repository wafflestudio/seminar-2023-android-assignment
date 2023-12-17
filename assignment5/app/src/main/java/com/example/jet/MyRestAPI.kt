package com.example.jet
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MyRestAPI {
    @GET("/3/search/movie")
    suspend fun getMoviesByQuery(
        @Query(value = "query") query: String,
        @Query(value = "include_adult") adult: Boolean,
        @Query(value = "page") page: Int,
    ):MyDataTypes.SearchResponse
    /*
    @GET("/3/authentication")
    suspend fun checkAPIKey(@Header("Authorization") token: String?):MyDataTypes.APIResponse


    @GET("/3/movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(@Header("Authorization") token: String?):MyDataTypes.MoviePageInfo*/
}