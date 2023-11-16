package com.wafflestudio.assignment4

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieData(
    @Json(name = "is_adult") val isAdult: Boolean,
    @Json(name = "background_image") val backdropPath: String,
    @Json(name = "genres") val genreIds: IntArray,
    @Json(name = "movie_identifier") val movieId: Int,
    @Json(name = "lang") val language: String,
    @Json(name = "original_name") val originalTitle: String,
    @Json(name = "synopsis") val overview: String,
    @Json(name = "pop_index") val popularityIndex: Double,
    @Json(name = "poster_image") val posterUrl: String,
    @Json(name = "date_of_release") val releaseDate: String,
    @Json(name = "movie_name") val title: String,
    @Json(name = "has_trailer") val hasVideo: Boolean,
    @Json(name = "rating_average") val voteAverage: Double,
    @Json(name = "votes_total") val totalVotes: Int
) : Parcelable {
constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.createIntArray()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isAdult) 1 else 0)
        parcel.writeString(backdropPath)
        parcel.writeIntArray(genreIds)
        parcel.writeInt(movieId)
        parcel.writeString(language)
        parcel.writeString(originalTitle)
        parcel.writeString(overview)
        parcel.writeDouble(popularityIndex)
        parcel.writeString(posterUrl)
        parcel.writeString(releaseDate)
        parcel.writeString(title)
        parcel.writeByte(if (hasVideo) 1 else 0)
        parcel.writeDouble(voteAverage)
        parcel.writeInt(totalVotes)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieData

        if (isAdult != other.isAdult) return false
        if (backdropPath != other.backdropPath) return false
        if (!genreIds.contentEquals(other.genreIds)) return false
        if (movieId != other.movieId) return false
        if (language != other.language) return false
        if (originalTitle != other.originalTitle) return false
        if (overview != other.overview) return false
        if (popularityIndex != other.popularityIndex) return false
        if (posterUrl != other.posterUrl) return false
        if (releaseDate != other.releaseDate) return false
        if (title != other.title) return false
        if (hasVideo != other.hasVideo) return false
        if (voteAverage != other.voteAverage) return false
        return totalVotes == other.totalVotes
    }

    override fun hashCode(): Int {
        var result = isAdult.hashCode()
        result = 31 * result + backdropPath.hashCode()
        result = 31 * result + genreIds.contentHashCode()
        result = 31 * result + movieId
        result = 31 * result + language.hashCode()
        result = 31 * result + originalTitle.hashCode()
        result = 31 * result + overview.hashCode()
        result = 31 * result + popularityIndex.hashCode()
        result = 31 * result + posterUrl.hashCode()
        result = 31 * result + releaseDate.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + hasVideo.hashCode()
        result = 31 * result + voteAverage.hashCode()
        result = 31 * result + totalVotes
        return result
    }

    companion object CREATOR : Parcelable.Creator<MovieData> {
        override fun createFromParcel(parcel: Parcel): MovieData {
            return MovieData(parcel)
        }

        override fun newArray(size: Int): Array<MovieData?> {
            return arrayOfNulls(size)
        }
    }
}

