package com.wafflestudio.assignment4.data


interface AuthRepository {

    suspend fun authenticateApiKey(accept: String, authorization: String): Boolean
}