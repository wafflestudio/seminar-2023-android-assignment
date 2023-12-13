package com.wafflestudio.assignment5.data

import com.wafflestudio.assignment5.lib.network.dto.MovieDetail
import com.wafflestudio.assignment5.lib.network.dto.SearchedMovieDto

interface MovieRepository {

    suspend fun getSearchedMovies(query: String, page: Int): List<MovieDetail>

    suspend fun getTotalMovies(query: String): Int
}