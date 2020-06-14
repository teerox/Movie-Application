package com.example.movieapp.datasource

import androidx.lifecycle.LiveData
import com.example.movieapp.database.MovieDAO
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor (private val movieDAO: MovieDAO):MovieDataSource<MovieResult> {

    override fun getLocal(): LiveData<List<MovieResult>> {
      return movieDAO.getAll()
    }

    override suspend fun save(item: MovieResult) {
        movieDAO.insertAll(item)
    }


    override suspend fun delete(item: Long) {
       movieDAO.deleteMovieById(item)
    }

    override suspend fun getFavourite(id:Long) :Boolean {
        return withContext(Dispatchers.IO) {
            movieDAO.isFavourite(id).isNotEmpty()
        }
    }


}