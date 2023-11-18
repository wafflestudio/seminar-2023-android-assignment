package com.example.movie

import retrofit2.http.GET
import retrofit2.http.Header

interface MyRestAPI {
    @GET("/3/authentication")
    suspend fun checkAPIKey(@Header("Authorization") token: String?):MyDataTypes.APIResponse
}