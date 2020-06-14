package com.example.movieapp.datasource

import androidx.lifecycle.LiveData
import com.example.movieapp.model.MovieResult
import com.example.movieapp.model.VideoResult

interface MovieDataSource<T> {

    fun getLocal(): LiveData<List<T>>? {
        return null
    }

    suspend fun getAllPopularMovies(pageNo:Int): List<T>?{
        return null
    }

    suspend fun getAllTopRatedMovies(pageNo:Int): List<T>?{
        return null
    }


    suspend fun getAllUpcomingMovies(pageNo:Int): List<T>?{
        return null
    }


    suspend fun getAllNowPlayingMovie(pageNo:Int): List<T>?{
        return null
    }
    suspend fun getSearchedMovies(pageNo:Int,searchParameter:String): List<T>?{
        return null
    }

    suspend fun getAllVideos(movie: MovieResult): List<VideoResult>?{
        return null
    }

    suspend fun save(item: T){

    }

    suspend fun deleteAll(){

    }

    suspend fun delete(item: Long){

    }

    suspend fun getFavourite(id:Long):Boolean{
        return false
    }


//
//    suspend fun getVideo(id:Long):List<T>{
//        return listOf<T>()
//    }

}