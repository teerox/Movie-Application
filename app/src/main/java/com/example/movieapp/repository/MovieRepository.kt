package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.Result
import com.example.movieapp.di.ResultMv

interface MovieRepository{

    suspend fun getMovies(): List<Result>?{
        return listOf()
    }

    suspend fun saveMovie(movie: Result)

    suspend fun deleteMovie(movieId: Long){
        TODO("not Implemented")
    }

    fun getFavouriteMovies(): LiveData<List<Result>>{
        var go = MutableLiveData<List<Result>>()
        return go
    }

    suspend fun isfavourite(id:Long):Boolean

    suspend fun getVideos(movie: Result):List<ResultMv>

}