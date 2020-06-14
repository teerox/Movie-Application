package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.MovieResult
import com.example.movieapp.model.VideoResult

interface MovieRepository<T>{

    fun getLocal(): LiveData<List<T>>

    suspend fun getAllPopularMovies(pageNo:Int): List<T>?


    suspend fun getAllTopRatedMovies(pageNo:Int): List<T>?


    suspend fun getAllUpcomingMovies(pageNo:Int): List<T>?


    suspend fun getAllNowPlayingMovie(pageNo:Int): List<T>?


    suspend fun getSearchedMovies(pageNo:Int,searchParameter:String): List<T>?


    suspend fun getAllVideos(movie: MovieResult): List<VideoResult>?


    suspend fun saveMovie(item: T)


    suspend fun deleteMovie(item: Long)


    suspend fun isFavourite(id:Long):Boolean
}