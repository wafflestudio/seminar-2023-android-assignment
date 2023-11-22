package com.wafflestudio.assignment4.data

import com.wafflestudio.assignment4.lib.network.MovieRestApi
import com.wafflestudio.assignment4.lib.network.dto.MovieDetailDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val api: MovieRestApi,
): MainRepository {

    override suspend fun getPopularMovies(language: String, page: Int, accept: String, authorization: String): List<MovieDetailDto> {

        return api.fetchPopularMovies(language, page, accept, authorization).body()!!.results
    }
}