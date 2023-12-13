package com.wafflestudio.assignment4.data

import com.wafflestudio.assignment4.lib.network.dto.MovieDetailDto

interface MainRepository {

    suspend fun getPopularMovies(language: String, page: Int, accept: String, authorization: String): List<MovieDetailDto>
}