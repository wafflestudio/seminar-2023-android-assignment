package com.wafflestudio.assignment4.lib.network

import com.wafflestudio.assignment4.lib.network.dto.BasicApiResponse
import com.wafflestudio.assignment4.lib.network.dto.MovieDetailDto
import com.wafflestudio.assignment4.lib.network.dto.PopularMoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MovieRestApi {

    @GET("3/movie/popular?language={language}&page={page}")
    suspend fun fetchPopularMovies(@Path("language") language: String,
                                   @Path("page") page: Int): Response<PopularMoviesDto>

    @GET("3/authentication")
    suspend fun authenticateApiKey(@Header("accept") accept: String = "application/json",
                                   @Header("Authorization") authorization: String) : Response<BasicApiResponse>
}