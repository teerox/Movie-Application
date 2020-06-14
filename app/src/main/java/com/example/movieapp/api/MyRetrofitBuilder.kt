package com.example.movieapp.api

import android.util.Log
import com.example.movieapp.model.Result
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitBuilder {


    private const val BASE_URL = "https://api.themoviedb.org/4/"
    const val API_KEY = "fb97e27952573c39dd8c56b40023750e"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    private val httpClient = okhttp3.OkHttpClient()
    //https://api.themoviedb.org/4/discover/movie?sort_by=popularity.desc&api_key=fb97e27952573c39dd8c56b40023750e
    private fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .build()
    }

    fun provideMovieApi(): ApiService{
        return provideRetrofit().create(ApiService::class.java)
    }


    //val base_url = "https://api.themoviedb.org/3/movie/${movie.uid}/videos?api_key=fb97e27952573c39dd8c56b40023750e&language=en-US"
    const val VIDEO_BASE_URL ="https://api.themoviedb.org/3/movie/"
    private val httpClient2 = okhttp3.OkHttpClient()

    private fun provideRetrofit2(movie: Result):Retrofit{
        Log.e("Movie-ID",movie.uid.toString())
        return Retrofit.Builder()
            .baseUrl(VIDEO_BASE_URL +"${movie.uid}/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient2)
            .build()
    }

    fun provideMovieApi2(movie: Result): ApiService{
        return provideRetrofit2(movie).create(ApiService::class.java)
    }
}