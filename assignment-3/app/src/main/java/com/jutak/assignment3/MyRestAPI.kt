package com.jutak.assignment3


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.GET

@JsonClass(generateAdapter = true)
data class Person(
    @Json(name = "name") val name :String,
    @Json(name = "age") val age: String,
)
interface MyRestAPI {
    @GET("/myapp/v1/info")
    fun getPerson(): Call<Person>
}