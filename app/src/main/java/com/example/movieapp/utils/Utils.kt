package com.example.movieapp.utils

import android.content.Context
import android.net.ConnectivityManager

object Utils {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "fb97e27952573c39dd8c56b40023750e"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    const val VIDEO_BASE_URL ="https://api.themoviedb.org/3/movie/"

    fun rating(num:Double):Float{
        var rate = num/2
        rate = String.format("%.1f", rate).toDouble()
        return rate.toFloat()
    }

    fun isNetworkAvailable(context: Context): Boolean? {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        if (activeNetwork != null) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                return true
            } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                return true
            }
        } else {
            return false
        }
        return false
    }
}

