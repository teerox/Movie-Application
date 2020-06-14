package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import com.example.movieapp.model.Result
import com.example.movieapp.di.ResultMv
import com.example.movieapp.datasource.MovieLocalDataSource
import com.example.movieapp.di.MovieRemoteDataSource
import kotlinx.coroutines.*


class DefaultMoviesRepository (private var taskLocalDataSource: MovieLocalDataSource,
                               private val tasksRemoteDataSource: MovieRemoteDataSource, private var ioDispatcher: CoroutineDispatcher = Dispatchers.IO):MovieRepository {
    override suspend fun saveMovie(movie: Result) {



        var a = getMovies()
        withContext(ioDispatcher) {
            coroutineScope {
                launch { taskLocalDataSource.save(movie) }
            }
        }
    }

    override suspend fun deleteMovie(movieId: Long) {
        withContext(ioDispatcher) {
            coroutineScope {
                launch { taskLocalDataSource.delete(movieId) }
            }
        }
    }


    override fun getFavouriteMovies(): LiveData<List<Result>> {
       return taskLocalDataSource.getLocal()
    }

    override suspend fun isfavourite(id: Long): Boolean{
        return taskLocalDataSource.getfav(id)
    }

    override suspend fun getVideos(movie: Result): List<ResultMv> {
       return tasksRemoteDataSource.getVideo(movie)
    }


    override suspend fun getMovies(): List<Result>? {
        return tasksRemoteDataSource.getAll()

    }


}
