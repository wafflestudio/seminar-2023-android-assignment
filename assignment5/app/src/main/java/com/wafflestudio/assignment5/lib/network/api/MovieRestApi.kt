package com.wafflestudio.assignment5.lib.network.api

import com.wafflestudio.assignment5.lib.network.dto.SearchedMovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRestApi {

    @GET("3/search/movie")
    suspend fun fetchSearchedMovies(@Query("query") query: String,
                                    @Query("include_adult") includeAdult: Boolean?,
                                    @Query("language") language: String?,
                                    @Query("primary_release_year") primaryReleaseYear: String?,
                                    @Query("page") page: Int?,
                                    @Query("region") region: String?,
                                    @Query("year") year: String?,
                                    @Header("accept") accept: String,
                                    @Header("Authorization") authorization: String) : Response<SearchedMovieDto>
}