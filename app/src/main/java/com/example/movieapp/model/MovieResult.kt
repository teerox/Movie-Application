package com.example.movieapp.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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
data class MovieResult (
    val id: String,

    @SerializedName("iso_639_1")
    val iso639_1: String,

    @SerializedName("iso_3166_1")
    val iso3166_1: String,

    val key: String,
    val name: String,
    val site: String,
    val size: Long,
    val type: String
):Parcelable

@Parcelize
@Entity(tableName = "favouritemovies")
data class Result (

    @SerializedName("poster_path")
    val posterPath: String,


    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @ColumnInfo(name = "movieName")
    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo
    @SerializedName("release_date")
    val releaseDate: String,


    @ColumnInfo(name = "isFavourite")
    var isFavourite:Boolean,

    @SerializedName("id")
    @PrimaryKey()var uid:Long


):Parcelable{

}

