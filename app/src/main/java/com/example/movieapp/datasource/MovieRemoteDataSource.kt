package com.example.movieapp.datasource

import com.example.movieapp.api.ApiService


import com.example.movieapp.model.MovieResult
import com.example.movieapp.model.VideoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Error
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val apiService: ApiService): MovieDataSource<MovieResult> {


    override suspend fun getAllPopularMovies(pageNo:Int): List<MovieResult>? {
        var data = listOf<MovieResult>()
        withContext(Dispatchers.IO){
            try {
                 data = apiService.getAllPopularMoviesAsync(page = pageNo).await().results

            }catch (e: Error){
                e.printStackTrace()
            }
        }
        return data.toList()
    }

    override suspend fun getAllTopRatedMovies(pageNo: Int): List<MovieResult>? {
        var data = listOf<MovieResult>()
        withContext(Dispatchers.IO){
            try {
                data = apiService.getAllTopRatedMoviesAsync(page = pageNo).await().results

            }catch (e: Error){
                e.printStackTrace()
            }
        }
        return data.toList()
    }

    override suspend fun getAllUpcomingMovies(pageNo: Int): List<MovieResult>? {
        var data = listOf<MovieResult>()
        withContext(Dispatchers.IO){
            try {
                data = apiService.getAllUpcomingMoviesAsync(page = pageNo).await().results

            }catch (e: Error){
                e.printStackTrace()
            }
        }
        return data.toList()
    }

    override suspend fun getAllNowPlayingMovie(pageNo: Int): List<MovieResult>? {
        var data = listOf<MovieResult>()
        withContext(Dispatchers.IO){
            try {
                data = apiService.getAllNowPlayingMoviesAsync(page = pageNo).await().results

            }catch (e: Error){
                e.printStackTrace()
            }
        }
        return data.toList()
    }

    override suspend fun getSearchedMovies(
        pageNo: Int,
        searchParameter: String
    ): List<MovieResult>? {
        var data = listOf<MovieResult>()
        withContext(Dispatchers.IO){
            try {
                data = apiService.getSearchedMoviesAsync(page = pageNo,searchParameter = searchParameter).await().results

            }catch (e: Error){
                e.printStackTrace()
            }
        }
        return data.toList()
    }

    override suspend fun getAllVideos(movie: MovieResult): List<VideoResult>? {
        var response = listOf<VideoResult>()
        withContext(Dispatchers.IO) {
            try {
                val videos = apiService.getVideosAsync(movieId = movie.id).await().results
                response = videos
            } catch (e: Error) {
                e.printStackTrace()
            }
        }
        return response.toList()
    }


}