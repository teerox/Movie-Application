package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.MovieResult
import com.example.movieapp.model.VideoResult

interface MovieRepository<T>{

    fun getLocal(): LiveData<List<MovieResult>>

    fun getAllPopularMovies(pageNo:Int): MutableLiveData<T>?


    fun getAllTopRatedMovies(pageNo:Int): MutableLiveData<T>?


    fun getAllUpcomingMovies(pageNo:Int): MutableLiveData<T>?


    fun getAllNowPlayingMovie(pageNo:Int): MutableLiveData<T>?


    fun getSearchedMovies(pageNo:Int,searchParameter:String): MutableLiveData<T>?


    fun getAllVideos(movie: MovieResult): MutableLiveData<VideoResult>?


    suspend fun saveMovie(item: MovieResult)


    suspend fun deleteMovie(item: Int)


    suspend fun isFavourite(id: Int):Boolean
}