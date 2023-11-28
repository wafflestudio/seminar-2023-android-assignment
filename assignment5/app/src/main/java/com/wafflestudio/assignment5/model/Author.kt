package com.wafflestudio.assignment5.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Author(
    @Json(name = "avatar_path") val avatarPath: String?,
    @Json(name = "rating") val rating: String?,
) : Parcelable
