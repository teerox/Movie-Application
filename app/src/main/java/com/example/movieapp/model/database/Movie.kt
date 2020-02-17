package com.example.movieapp.model.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @ColumnInfo(name = "movieName")
    var movieName:String,
    @ColumnInfo(name = "movieRating")
    var movieRating:String,
    @ColumnInfo(name = "movieReleaseDate")
    var movieReleaseDate:String,
    @ColumnInfo(name = "movieImage")
    var movieImage:String

): Parcelable
{
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)var uid:Int = 0

}