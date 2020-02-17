package com.example.movieapp.model.database


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.internal.*

@Parcelize
data class MovieFeeds (
    val page: Long,

    @SerializedName("total_results")
    val totalResults: Long,

    @SerializedName("total_pages")
    val totalPages: Long,

    val results: List<Result>
):Parcelable

@Parcelize
data class Result (
    val popularity: Double,

    @SerializedName("vote_count")
    val voteCount: Long,

    val video: Boolean,

    @SerializedName("poster_path")
    val posterPath: String,

    val id: Long,
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("original_language")
    val originalLanguage: OriginalLanguage,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("genre_ids")
    val genreIDS: List<Long>,

    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String
):Parcelable

@Serializable(with = OriginalLanguage.Companion::class)
enum class OriginalLanguage(val value: String) {
    CN("cn"),
    En("en"),
    Ko("ko");

    companion object : KSerializer<OriginalLanguage> {
        override val descriptor: SerialDescriptor get() {
            return StringDescriptor
        }
        override fun deserialize(decoder: Decoder): OriginalLanguage = when (decoder.decodeString()) {
            "cn" -> CN
            "en" -> En
            "ko" -> Ko
            else -> throw IllegalArgumentException()
        }
        override fun serialize(encoder: Encoder, obj: OriginalLanguage) {
            return encoder.encodeString(obj.value)
        }
    }
}
