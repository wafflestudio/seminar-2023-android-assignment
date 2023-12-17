package com.wafflestudio.assignment4.network

import com.wafflestudio.assignment4.network.dto.login.StatusResponse
import com.wafflestudio.assignment4.network.dto.movie.GetPopularMovies
import com.wafflestudio.assignment4.network.dto.movie.Movie
import retrofit2.http.GET

interface MyRestAPI {
    @GET("/3/authentication")
    suspend fun authenticate(): StatusResponse

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): GetPopularMovies
}