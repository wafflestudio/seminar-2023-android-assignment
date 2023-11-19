package com.example.movie

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.Date

sealed class MyDataTypes{
    @JsonClass(generateAdapter = true)
    data class APIResponse(
        @Json(name = "success") val success: Boolean,
        @Json(name = "status_code") val status_code: Int,
        @Json(name = "status_message") val status_message: String,
    )
    @JsonClass(generateAdapter = true)
    data class MoviePageInfo(
        @Json(name="page")val page:Int,
        @Json(name="results")val results:List<MovieInfo>,
        @Json(name="total_pages")val total_pages:Int,
        @Json(name="total_results")val total_results:Int,
    )
    @JsonClass(generateAdapter = true)
    @Parcelize
    data class MovieInfo(
        @Json(name="adult")val adult:Boolean,
        @Json(name="backdrop_path")val backdrop_path:String,
        @Json(name="genre_ids")val genre_ids:List<Int>,
        @Json(name="id")val id:Int,
        @Json(name="original_language")val original_language:String,
        @Json(name="original_title")val original_title:String,
        @Json(name="overview")val overview:String,
        @Json(name="popularity")val popularity:Float,
        @Json(name="poster_path")val poster_path:String,
        @Json(name="release_date")val release_date: String,
        @Json(name="title")val title:String,
        @Json(name="video")val video:Boolean,
        @Json(name="vote_average")val vote_average:Float,
        @Json(name="vote_count")val vote_count:Int,
    ): Parcelable
    /*
    {
      "adult": false,
      "backdrop_path": "/fm6KqXpk3M2HVveHwCrBSSBaO0V.jpg",
      "genre_ids": [
        18,
        36
      ],
      "id": 872585,
      "original_language": "en",
      "original_title": "Oppenheimer",
      "overview": "The story of J. Robert Oppenheimer's role in the development of the atomic bomb during World War II.",
      "popularity": 2464.545,
      "poster_path": "/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg",
      "release_date": "2023-07-19",
      "title": "Oppenheimer",
      "video": false,
      "vote_average": 8.205,
      "vote_count": 4581
    }
    */

}
