package com.jutak.assignment3


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MyRestAPI {
    @GET("word_lists")
    suspend fun getWordBookList(): List<WordBook>

    @POST("word_list")
    suspend fun wordBookCreate(@Body data: CreateWordBook): Response<List<WordBook>>

    @GET("word_list/{id}")
    suspend fun getWordListById(@Path("id") id :Int):WordList

    @POST("word_list/{id}/permission")
    suspend fun getPermission(@Body password: Password,@Path("id") id :Int):Permission

    @HTTP(method = "DELETE",path = "word_list/{id}", hasBody = true)
    suspend fun deleteWordBook(@Body password: Password,@Path("id") id :Int)

    @PUT("word_list/{id}")
    suspend fun wordCreate(@Path("id") id:Int, @Body data:CreateWord):WordList
}