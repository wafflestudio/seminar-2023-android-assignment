package com.wafflestudio.assignment5.network

import com.wafflestudio.assignment5.network.dto.GetSearchMovieResult
import com.wafflestudio.assignment5.network.dto.GetSearchMovieReviewResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyRestAPI {

    @GET("/3/search/movie")
    suspend fun searchMovie(
        @Query(value = "query") query: String,
        @Query(value = "include_adult") adult: Boolean,
        @Query(value = "page") page: Int,
    ): GetSearchMovieResult

    @GET("/3/movie/{movie_id}/reviews")
    suspend fun searchReview(
        @Path(value = "movie_id") id: Int,
        @Query(value = "page") page: Int,
    ): GetSearchMovieReviewResult
}