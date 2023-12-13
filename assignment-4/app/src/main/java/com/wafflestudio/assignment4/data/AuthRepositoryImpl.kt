package com.wafflestudio.assignment4.data

import com.wafflestudio.assignment4.lib.network.MovieRestApi
import com.wafflestudio.assignment4.lib.network.dto.MovieDetailDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val api: MovieRestApi,
): AuthRepository {

    override suspend fun authenticateApiKey(accept: String, authorization: String): Boolean {
        val authValue = api.authenticateApiKey(accept, authorization).body()?.success ?: false
        return authValue
    }
}