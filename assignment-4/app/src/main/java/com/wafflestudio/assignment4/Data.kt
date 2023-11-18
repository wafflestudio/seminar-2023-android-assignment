package com.wafflestudio.assignment4

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class Data {

    @JsonClass(generateAdapter = true)
    data class LoginResult(
        @Json(name="success") val success:Boolean,
        @Json(name="status_code") val code:Int,
        @Json(name="status_message") val message:String
    )

    @JsonClass(generateAdapter = true)
    data class Movies(
        @Json(name = "page") val page:Int,
        @Json(name = "results") val result:List<MovieInfo>,
        @Json(name = "total_pages") val total_pages:Int,
        @Json(name = "total_results") val total_results:Int
    )

    @JsonClass(generateAdapter = true)
    data class MovieInfo(
        @Json(name = "adult") val adult:Boolean,
        @Json(name = "backdrop_path") val backdrop_path:String,
        @Json(name = "genre_ids") val genre_ids:Array<Int>,
        @Json(name = "id") val id:Int,
        @Json(name = "original_language") val original_language:String,
        @Json(name = "original_title") val original_title:String,
        @Json(name = "overview") val overview:String,
        @Json(name = "popularity") val popularity:Float,
        @Json(name = "poster_path") val poster_path:String,
        @Json(name = "release_date") val release_date:String,
        @Json(name = "title") val title:String,
        @Json(name = "video") val video:Boolean,
        @Json(name = "vote_average") val vote_average:Float,
        @Json(name = "vote_count") val vote_count:Int
    )
}