package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.MovieResult
import com.example.movieapp.model.VideoResult

interface MovieRepository<T>{

    fun getLocal(): LiveData<List<MovieResult>>

    suspend fun getAllPopularMovies(pageNo:Int): MutableLiveData<T>?


    suspend fun getAllTopRatedMovies(pageNo:Int): MutableLiveData<T>?


    suspend fun getAllUpcomingMovies(pageNo:Int): MutableLiveData<T>?


    suspend fun getAllNowPlayingMovie(pageNo:Int): MutableLiveData<T>?


    suspend fun getSearchedMovies(pageNo:Int,searchParameter:String): MutableLiveData<T>?


    suspend fun getAllVideos(movie: MovieResult): MutableLiveData<VideoResult>?


    suspend fun saveMovie(item: MovieResult)


    suspend fun deleteMovie(item: Long)


    suspend fun isFavourite(id:Long):Boolean
}