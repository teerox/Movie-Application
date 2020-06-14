package com.example.movieapp.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.MovieResult
import com.example.movieapp.model.VideoResult
import retrofit2.Call

interface MovieDataSource<T> {

    fun getLocal(): LiveData<List<T>>? {
        return null
    }

    fun getAllPopularMovies(pageNo:Int): Call<T>?{
        return null
    }

    fun getAllTopRatedMovies(pageNo:Int): Call<T>?{
        return null
    }


    fun getAllUpcomingMovies(pageNo:Int): Call<T>?{
        return null
    }


    fun getAllNowPlayingMovie(pageNo:Int): Call<T>?{
        return null
    }
    fun getSearchedMovies(pageNo:Int,searchParameter:String): Call<T>?{
        return null
    }

    fun getAllVideos(movie: MovieResult): Call<VideoResult>?{
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