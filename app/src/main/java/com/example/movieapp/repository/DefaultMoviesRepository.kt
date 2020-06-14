package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import com.example.movieapp.datasource.MovieLocalDataSource
import com.example.movieapp.datasource.MovieRemoteDataSource
import com.example.movieapp.model.MovieResult
import com.example.movieapp.model.VideoResult
import kotlinx.coroutines.*
import javax.inject.Inject


class DefaultMoviesRepository @Inject constructor (private var taskLocalDataSource: MovieLocalDataSource,
                               private val tasksRemoteDataSource: MovieRemoteDataSource):MovieRepository<MovieResult> {

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


    override suspend fun getAllPopularMovies(pageNo: Int): List<MovieResult>? {
        return tasksRemoteDataSource.getAllPopularMovies(pageNo)
    }



    override suspend fun getAllTopRatedMovies(pageNo: Int): List<MovieResult>? {
       return tasksRemoteDataSource.getAllTopRatedMovies(pageNo)
    }



    override suspend fun getAllUpcomingMovies(pageNo: Int): List<MovieResult>? {
       return tasksRemoteDataSource.getAllUpcomingMovies(pageNo)
    }



    override suspend fun getAllNowPlayingMovie(pageNo: Int): List<MovieResult>? {
        return tasksRemoteDataSource.getAllNowPlayingMovie(pageNo)
    }



    override suspend fun getSearchedMovies(
        pageNo: Int,
        searchParameter: String
    ): List<MovieResult>? {
        return tasksRemoteDataSource.getSearchedMovies(pageNo,searchParameter)
    }



    override suspend fun getAllVideos(movie: MovieResult): List<VideoResult>? {
       return tasksRemoteDataSource.getAllVideos(movie)
    }



    override suspend fun saveMovie(item: MovieResult) {
        taskLocalDataSource.save(item)
    }



    override suspend fun isFavourite(id: Long): Boolean {
       return taskLocalDataSource.getFavourite(id)
    }


}
