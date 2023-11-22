package com.wafflestudio.assignment4.lib.network

import com.wafflestudio.assignment4.lib.network.dto.BasicApiResponse
import com.wafflestudio.assignment4.lib.network.dto.MovieDetailDto
import com.wafflestudio.assignment4.lib.network.dto.PopularMoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRestApi {

    @GET("3/movie/popular")
    suspend fun fetchPopularMovies(@Query("language") language: String,
                                   @Query("page") page: Int,
                                   @Header("accept") accept: String,
                                   @Header("Authorization") authorization: String) : Response<PopularMoviesDto>

    @GET("3/authentication")
    suspend fun authenticateApiKey(@Header("accept") accept: String,
                                   @Header("Authorization") authorization: String) : Response<BasicApiResponse>
}