package com.wafflestudio.assignment4.data

import android.util.Log
import com.wafflestudio.assignment4.lib.network.MovieRestApi
import com.wafflestudio.assignment4.lib.network.dto.MovieDetailDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val api: MovieRestApi,
): MainRepository {

    override suspend fun getPopularMovies(language: String, page: Int, accept: String, authorization: String): List<MovieDetailDto> {
        val response = api.fetchPopularMovies(language, page, accept, authorization)
        if (response.isSuccessful) {
            Log.e("Repository", "Success code: ${response.code()}")
        } else {
            Log.e("Repository", "Failed to fetch popular movies: ${response.code()}")
        }
        return response.body()?.results ?: emptyList()
    }
}