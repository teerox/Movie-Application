package com.example.movieapp.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.api.MyRetrofitBuilder
import com.example.movieapp.datasource.MovieDataSource
import com.example.movieapp.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Error

class MovieRemoteDataSource internal constructor(private var ioDispatcher: CoroutineDispatcher = Dispatchers.IO):
    MovieDataSource<Result> {

    private val apiService = MyRetrofitBuilder.provideMovieApi()
    private val observableMovies = MutableLiveData<List<Result>>()


    override fun getLocal(): LiveData<List<Result>> {
       return observableMovies
    }

    override suspend fun getAll(): List<Result>? {
        var data = listOf<Result>()
        withContext(ioDispatcher){
            try {
                val allmovie = apiService.getAllmoviesAsync().await().results
                data = allmovie
            }catch (e: Error){
                e.printStackTrace()
            }
        }
        return data.toList()
    }

    override suspend fun save(item: Result) {

    }

    override suspend fun deleteAll() {

    }

    override suspend fun delete(item: Long) {

    }

    override suspend fun getfav(id: Long): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    suspend fun getVideo(movie: Result): List<ResultMv> {
        val apiService2 = MyRetrofitBuilder.provideMovieApi2(movie)
        var response = listOf<ResultMv>()
        withContext(ioDispatcher) {
            try {
                val videos = apiService2.getVideosAsyn().await().results
                response = videos
            } catch (e: Error) {
                e.printStackTrace()
            }
        }
        return response.toList()
    }
}