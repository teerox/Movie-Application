package com.example.movieapp.model.api

import com.example.movieapp.model.database.MovieFeeds
import com.example.movieapp.model.database.Result
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("discover/movie")
fun getAllmoviesAsync(@Query("sort_by") filter :String = "popularity.desc",
                      @Query("api_key") api:String = MyRetrofitBuilder.API_KEY): Deferred<MovieFeeds>

    //https://api.themoviedb.org/4/discover/movie?sort_by=popularity.desc&api_key=fb97e27952573c39dd8c56b40023750e
    @GET("search/movie")
    suspend fun getSearchedMoviesAsync(
        @Query("sort_by") filter: String = "popularity.desc",
        @Query("api_key") api: String = MyRetrofitBuilder.API_KEY,
        @Query("query") key: String,
        @Query("page") page: Int = 200
    ):Deferred<MovieFeeds>
}
