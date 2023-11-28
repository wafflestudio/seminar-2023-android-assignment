package com.wafflestudio.assignment5.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Review(
    @Json(name = "id") val id: String,
    @Json(name = "author") val author: String,
    @Json(name = "author_details") val authorDetail: Author,
    @Json(name = "content") val content: String,
    @Json(name = "created_at") val createdAt: String,
) : Parcelable