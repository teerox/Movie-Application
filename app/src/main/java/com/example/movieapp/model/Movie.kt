package com.example.movieapp.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Movie(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
):Parcelable

@Parcelize
@Entity(tableName = "favouriteMovies")
data class MovieResult(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    @ColumnInfo
    val release_date: String,
    @ColumnInfo(name = "movieName")
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    @ColumnInfo(name = "isFavourite")
    var isFavourite:Boolean
): Parcelable
