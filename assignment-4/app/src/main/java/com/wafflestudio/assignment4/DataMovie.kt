package com.wafflestudio.assignment4

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataMovie (
    @Json(name = "overview") val overview: String,
    @Json(name = "poster_path") val poster_path: String,
    @Json(name = "release_date") val release_data: String,
    @Json(name = "title") val title: String,
    @Json(name = "vote_average") val vote_average: Number,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(overview)
        parcel.writeString(poster_path)
        parcel.writeString(release_data)
        parcel.writeString(title)
        parcel.writeDouble(vote_average.toDouble())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataMovie> {
        override fun createFromParcel(parcel: Parcel): DataMovie {
            return DataMovie(parcel)
        }

        override fun newArray(size: Int): Array<DataMovie?> {
            return arrayOfNulls(size)
        }
    }
}