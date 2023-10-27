package com.jutak.assignment3
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    suspend fun getWordListInfo(): List<MyData.WordListInfo>

    @GET("/myapp/v1/word_list/{id}")
    suspend fun getWordList(@Path("id") id: Int): MyData.WordList

    @POST("/myapp/v1/word_list")
    suspend fun createWordList(@Body data: MyData.WordListPostInfo): List<MyData.WordListInfo>

    @POST("/myapp/v1/word_list/{id}/permission")
    suspend fun verifyPermission(@Path("id") id: Int, @Body password: String): Boolean

    @DELETE("/myapp/v1/word_list/{id}")
    suspend fun deleteWordList(@Path("id") id: Int, @Body password: String): Response<String>

    @PUT("/myapp/v1/word_list/{id}")
    suspend fun putWord(@Path("id") id: Int, @Body word: MyData.WordInfo): MyData.WordList

}