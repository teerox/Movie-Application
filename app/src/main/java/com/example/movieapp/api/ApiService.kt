package com.example.movieapp.api


import com.example.movieapp.model.Movie
import com.example.movieapp.model.Video
import com.example.movieapp.utils.Utils.API_KEY
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService{

    @GET("movie/popular")
    fun getAllPopularMoviesAsync(
        @Query("api_key") api:String = API_KEY,
        @Query("page") page: Int): Deferred<Movie>


    @GET("movie/top_rated")
    fun getAllTopRatedMoviesAsync(
        @Query("api_key") api:String = API_KEY,
        @Query("page") page: Int): Deferred<Movie>


    @GET("movie/upcoming")
    fun getAllUpcomingMoviesAsync(
        @Query("api_key") api:String = API_KEY,
        @Query("page") page: Int): Deferred<Movie>


    @GET("movie/now_playing")
    fun getAllNowPlayingMoviesAsync(
        @Query("api_key") api:String = API_KEY,
        @Query("page") page: Int): Deferred<Movie>


    @GET("search/movie")
    fun getSearchedMoviesAsync(
        @Query("api_key") api: String = API_KEY,
        @Query("query") searchParameter: String,
        @Query("page") page: Int
    ):Deferred<Movie>


    @GET("movie/")
     fun getVideosAsync(
        @Path("movie_id") movieId:Int,
        @Path("videos") videos:String = "videos",
        @Query("api_key") api:String = API_KEY,
        @Query("language") language:String = "en-US"): Deferred<Video>

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

//allMovies
//https://api.themoviedb.org/4/discover/movie?sort_by=popularity.desc&api_key=fb97e27952573c39dd8c56b40023750e
