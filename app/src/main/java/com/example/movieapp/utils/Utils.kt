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

//use total_pages
//popular 500pages
//https://api.themoviedb.org/3/movie/popular?api_key=fb97e27952573c39dd8c56b40023750e&language=en-US&page=1

//top rated 371pages
//https://api.themoviedb.org/3/movie/top_rated?api_key=fb97e27952573c39dd8c56b40023750e&language=en-US&page=1

//upcoming
//https://api.themoviedb.org/3/movie/upcoming?api_key=fb97e27952573c39dd8c56b40023750e&language=en-US&page=1

//now playing
//https://api.themoviedb.org/3/movie/now_playing?api_key=fb97e27952573c39dd8c56b40023750e&language=en-US&page=1

//search
//https://api.themoviedb.org/3/search/movie?api_key=fb97e27952573c39dd8c56b40023750e&language=en-US&query=moviename

//trailer movie
// "https://api.themoviedb.org/3/movie/${movie.uid}/videos?api_key=fb97e27952573c39dd8c56b40023750e&language=en-US"