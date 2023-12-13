package com.wafflestudio.assignment5.data

import com.wafflestudio.assignment5.lib.network.api.MovieRestApi
import com.wafflestudio.assignment5.lib.network.dto.MovieDetail
import com.wafflestudio.assignment5.lib.network.dto.SearchedMovieDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieRestApi,
): MovieRepository {

    val accept = "application/json"
    val apiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYzg5MWYxNWM4ZjU1NzI1YjE3NDA2Nzc3NGUzMzI0YiIsInN1YiI6IjY1NTQ4ZWE1NTM4NjZlMDBhYmFhYTk3MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.tuus7_u6jcLbVRl9UpWxF-uyEyR04J7UyAQRKfgf5Sw"

    override suspend fun getSearchedMovies(query: String, page: Int): List<MovieDetail> {
        return api.fetchSearchedMovies(query, true, "en-US", null, page, null, null, accept, apiKey).body()?.results ?: emptyList()
    }

    override suspend fun getTotalMovies(query: String): Int {
        return api.fetchSearchedMovies(query, true, "en-US", null, null, null, null, accept, apiKey).body()?.totalPages ?: 0
    }
}