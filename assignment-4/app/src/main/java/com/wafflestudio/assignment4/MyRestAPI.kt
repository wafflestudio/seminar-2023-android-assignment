package com.wafflestudio.assignment4

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface MyRestAPI {

    @Headers("Accept: application/json")
    @GET("authentication")
    suspend fun giveToken(@Header("Authorization") token: String):Data.loginResult
}