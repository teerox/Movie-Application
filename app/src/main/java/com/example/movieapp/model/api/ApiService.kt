package com.example.movieapp.model.api

import com.example.movieapp.model.database.Movie
import com.example.movieapp.model.database.MovieFeeds
import com.example.movieapp.model.database.Result
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("discover/movie")
fun getAllmoviesAsync(@Query("sort_by") filter :String = "popularity.desc",
                      @Query("api_key") api:String = MyRetrofitBuilder.API_KEY): Deferred<MovieFeeds>

}

