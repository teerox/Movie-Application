package com.example.movieapp.di

//import android.os.Parcelable
//import kotlinx.android.parcel.Parcelize
//import kotlinx.serialization.SerialName
//
//
//@Parcelize
//data class Welcome (
//    val id: Long,
//    val results: List<MovieResult>
//): Parcelable

import kotlinx.serialization.*

@Serializable
data class WelcomeMv (
    val id: Long,
    val results: List<ResultMv>
)

@Serializable
data class ResultMv (
    val id: String,

    @SerialName("iso_639_1")
    val iso639_1: String,

    @SerialName("iso_3166_1")
    val iso3166_1: String,

    val key: String,
    val name: String,
    val site: String,
    val size: Long,
    val type: String
)