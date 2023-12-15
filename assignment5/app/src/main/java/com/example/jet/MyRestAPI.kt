package com.example.jet
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MyRestAPI {
    @GET("/3/search/movie?query={query}&include_adult=false&language=en-US&page={page}")
    suspend fun getMoviesByQuery(@Path("query")query:String,@Path("page")page:Int):MyDataTypes.SearchResponse
    /*
    @GET("/3/authentication")
    suspend fun checkAPIKey(@Header("Authorization") token: String?):MyDataTypes.APIResponse


    @GET("/3/movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(@Header("Authorization") token: String?):MyDataTypes.MoviePageInfo*/
}