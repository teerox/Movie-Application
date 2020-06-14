package com.example.movieapp.datasource


import com.example.movieapp.api.ApiService
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieResult
import com.example.movieapp.model.VideoResult
import retrofit2.Call
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val apiService: ApiService): MovieDataSource<Movie> {


    override fun getAllPopularMovies(pageNo:Int): Call<Movie>? {
        return apiService.getAllPopularMoviesAsync(page = pageNo)
    }


    override fun getAllTopRatedMovies(pageNo: Int): Call<Movie>? {
        return apiService.getAllTopRatedMoviesAsync(page = pageNo)
    }


    override fun getAllUpcomingMovies(pageNo: Int): Call<Movie>? {
        return apiService.getAllUpcomingMoviesAsync(page = pageNo)
    }


    override fun getAllNowPlayingMovie(pageNo: Int): Call<Movie>? {
        return apiService.getAllNowPlayingMoviesAsync(page = pageNo)
    }


    override fun getSearchedMovies(
        pageNo: Int,
        searchParameter: String
    ): Call<Movie>? {
        return apiService.getSearchedMoviesAsync(page = pageNo,searchParameter = searchParameter)
    }


    override fun getAllVideos(movie: MovieResult): Call<VideoResult>? {
        return apiService.getVideosAsync(movieId = movie.id)
    }



}