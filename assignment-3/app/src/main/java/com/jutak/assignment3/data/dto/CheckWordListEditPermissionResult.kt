package com.jutak.assignment3.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CheckWordListEditPermissionResult(
    @Json(name = "valid") val valid: Boolean,
)
