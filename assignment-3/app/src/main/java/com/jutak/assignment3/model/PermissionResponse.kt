package com.jutak.assignment3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PermissionResponse(
    @Json(name = "valid") val valid: Boolean
)