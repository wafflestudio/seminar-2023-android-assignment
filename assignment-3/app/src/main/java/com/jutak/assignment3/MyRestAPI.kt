package com.jutak.assignment3

import android.graphics.ColorSpace.Model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


@JsonClass(generateAdapter = true)
data class Word(
    @Json(name="spell") val spell:String,
    @Json(name="meaning") val meaning:String,
    @Json(name="synonym") val synonym:String?,
    @Json(name="antonym") val antonym:String?,
    @Json(name="sentence") val sentence:String?
)
@JsonClass(generateAdapter = true)
data class GetModel(
    @Json(name="id") val id: Int,
    @Json(name="name") val name: String,
    @Json(name="owner") val owner: String,
    @Json(name="word_list") val word_list: List<Word>
)
@JsonClass(generateAdapter = true)
data class Data1(
    @Json(name="name") val name:String,
    @Json(name="owner") val owner:String,
    @Json(name="password") val password:String
)

@JsonClass(generateAdapter = true)
data class Data2(
    @Json(name="id") val id:Int,
)

@JsonClass(generateAdapter = true)
data class PostResult2(
    @Json(name="id") val id:Int,
    @Json(name="name") val name:String,
    @Json(name="owner") val owner:String
)

data class PostResult(
    val id:Int,
    val name:String,
    val owner:String
)
/*
@JsonClass(generateAdapter = true)
data class PostResult(
    @Json() val result:List<PostModel>
)

 */

interface MyRestAPI {
    @POST("word_list")
    fun word_list_create(@Body data:Data1): Call<PostResult>

    @GET("word_list/{id}")
    fun word_list_read(@Path("id") id:Int): Call<GetModel>
}
