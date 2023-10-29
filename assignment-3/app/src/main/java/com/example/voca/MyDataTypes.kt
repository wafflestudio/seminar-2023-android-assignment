package com.example.voca

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class MyDataTypes(val viewType: ViewType){
    @JsonClass(generateAdapter = true)
    data class VocaListInfo(
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String,
        @Json(name = "owner") val owner: String,
    ): MyDataTypes(ViewType.A)

    @JsonClass(generateAdapter = true)
    data class NewVocaList(
        @Json(name = "owner") val owner: String,
        @Json(name = "name") val name: String,
        @Json(name = "password") val password: String,
    )
    @JsonClass(generateAdapter = true)
    data class VocaListSpecificInfo(
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String?,
        @Json(name = "owner") val owner: String?,
        @Json(name = "word_list") val word_list: List<Voca>,
    ): Parcelable {
        constructor(parcel: Parcel) : this(
            TODO("id"),
            parcel.readString(),
            parcel.readString(),
            TODO("word_list")
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(owner)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<VocaListSpecificInfo> {
            override fun createFromParcel(parcel: Parcel): VocaListSpecificInfo {
                return VocaListSpecificInfo(parcel)
            }

            override fun newArray(size: Int): Array<VocaListSpecificInfo?> {
                return arrayOfNulls(size)
            }
        }
    }

    @JsonClass(generateAdapter = true)
    data class Voca (
        @Json(name = "spell") val spell: String,
        @Json(name = "meaning") val meaning: String,
        @Json(name = "synonym") val synonym: String?,
        @Json(name = "antonym") val antonym: String?,
        @Json(name = "sentence") val sentence: String?,
    ) : MyDataTypes(ViewType.B)
    enum class ViewType { A,B}
}
