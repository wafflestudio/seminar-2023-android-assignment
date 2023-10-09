package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


@JsonClass(generateAdapter = true)
data class Word(
    @Json(name="spell") val spell:String,
    @Json(name="meaning") val meaning:String,
    @Json(name="synonym") val synonym:String?,
    @Json(name="antonym") val antonym:String?,
    @Json(name="sentence") val sentence:String?
)
/*
@JsonClass(generateAdapter = true)
data class Data(
    @Json(name="name") val name:String,
    @Json(name="owner") val owner:String,
    @Json(name="password") val password:String
)

 */
data class Data(
    val name:String,
    val owner: String,
    val password:String
)
@JsonClass(generateAdapter = true)
data class PostResult(
    @Json(name="id") val id:Int,
    @Json(name="name") val name:String,
    @Json(name="owner") val owner:String
)

interface MyRestAPI {
    @POST("/wordlist")
    fun word_list_create(@Body data:Data): Call<PostResult>
}
