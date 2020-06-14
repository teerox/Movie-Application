package com.example.movieapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.datasource.MovieLocalDataSource
import com.example.movieapp.datasource.MovieRemoteDataSource
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieResult
import com.example.movieapp.model.VideoResult
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Error
import javax.inject.Inject


class DefaultMoviesRepository @Inject constructor (private var taskLocalDataSource: MovieLocalDataSource,
                               private val tasksRemoteDataSource: MovieRemoteDataSource):MovieRepository<Movie> {

    override suspend fun deleteMovie(item: Long) {
        withContext(Dispatchers.IO) {
            coroutineScope {
                launch { taskLocalDataSource.delete(item) }
            }
        }
    }


    override fun getLocal(): LiveData<List<MovieResult>> {
        return taskLocalDataSource.getLocal()
    }


    override suspend fun saveMovie(item: MovieResult) {
        taskLocalDataSource.save(item)
    }



    override suspend fun isFavourite(id: Long): Boolean {
       return taskLocalDataSource.getFavourite(id)
    }



    override suspend fun getAllPopularMovies(pageNo: Int): MutableLiveData<Movie>? {
        val popularMovies:MutableLiveData<Movie>? = MutableLiveData()
        try {
            val data = tasksRemoteDataSource.getAllPopularMovies(pageNo)
            data?.enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful){
                        popularMovies?.value = response.body()
                    }else{
                        Log.e("404 Error","404 Error")
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.e("Failed",t.message.toString())
                    popularMovies?.value = null
                }

            })

        }catch (e: Error){
            e.printStackTrace()
        }

        return popularMovies
    }



    override suspend fun getAllTopRatedMovies(pageNo: Int): MutableLiveData<Movie>? {
        val allTopRatedMovies:MutableLiveData<Movie>? = MutableLiveData()
        try {
            val data = tasksRemoteDataSource.getAllTopRatedMovies(pageNo)
            data?.enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful){
                        allTopRatedMovies?.value = response.body()
                    }else{
                        Log.e("404 Error","404 Error")
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.e("Failed",t.message.toString())
                    allTopRatedMovies?.value = null
                }

            })

        }catch (e: Error){
            e.printStackTrace()
        }

        return allTopRatedMovies
    }



    override suspend fun getAllUpcomingMovies(pageNo: Int): MutableLiveData<Movie>? {
        val allUpcomingMovies:MutableLiveData<Movie>? = MutableLiveData()
        try {
            val data = tasksRemoteDataSource.getAllUpcomingMovies(pageNo)
            data?.enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful){
                        allUpcomingMovies?.value = response.body()
                    }else{
                        Log.e("404 Error","404 Error")
                    }
                }
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.e("Failed",t.message.toString())
                    allUpcomingMovies?.value = null
                }

            })

        }catch (e: Error){
            e.printStackTrace()
        }

        return allUpcomingMovies
    }



    override suspend fun getAllNowPlayingMovie(pageNo: Int): MutableLiveData<Movie>? {

        val allNowPlayingMovie:MutableLiveData<Movie>? = MutableLiveData()
        try {
            val data = tasksRemoteDataSource.getAllNowPlayingMovie(pageNo)
            data?.enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful){
                        allNowPlayingMovie?.value = response.body()
                    }else{
                        Log.e("404 Error","404 Error")
                    }
                }
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.e("Failed",t.message.toString())
                    allNowPlayingMovie?.value = null
                }

            })

        }catch (e: Error){
            e.printStackTrace()
        }

        return allNowPlayingMovie

    }


    override suspend fun getSearchedMovies(
        pageNo: Int,
        searchParameter: String
    ): MutableLiveData<Movie>? {
        val searchedMovies:MutableLiveData<Movie>? = MutableLiveData()
        try {
            val data = tasksRemoteDataSource.getSearchedMovies(pageNo,searchParameter)
            data?.enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful){
                        searchedMovies?.value = response.body()
                    }else{
                        Log.e("404 Error","404 Error")
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.e("Failed",t.message.toString())
                    searchedMovies?.value = null
                }

            })

        }catch (e: Error){
            e.printStackTrace()
        }

        return searchedMovies
    }



    override suspend fun getAllVideos(movie: MovieResult): MutableLiveData<VideoResult>? {
        val allVideos:MutableLiveData<VideoResult>? = MutableLiveData()
        try {
            val data = tasksRemoteDataSource.getAllVideos(movie)
            data?.enqueue(object : Callback<VideoResult> {
                override fun onResponse(call: Call<VideoResult>, response: Response<VideoResult>) {
                    if (response.isSuccessful){
                        allVideos?.value = response.body()
                    }else{
                        Log.e("404 Error","404 Error")
                    }
                }

                override fun onFailure(call: Call<VideoResult>, t: Throwable) {
                    Log.e("Failed",t.message.toString())
                    allVideos?.value = null
                }

            })

        }catch (e: Error){
            e.printStackTrace()
        }

        return allVideos
    }



}
