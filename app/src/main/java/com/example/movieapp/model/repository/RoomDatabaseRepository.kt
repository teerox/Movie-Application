package com.example.movieapp.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.movieapp.model.database.Movie
import com.example.movieapp.model.database.MovieDAO
import com.example.movieapp.model.database.MovieDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class RoomDatabaseRepository(var context: Context):CoroutineScope {
    override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main

        private var movieDAO:MovieDAO
    init {
        val database = MovieDatabase.getInstance(context)
        movieDAO = database!!.MovieDao()
    }


    suspend fun addMovie(movie: Movie){
        withContext(Dispatchers.IO) {
            movieDAO.insertAll(movie)
        }
    }
    val allFavouriteMovie:LiveData<List<Movie>> = movieDAO.getAll()

    suspend fun deleteMovie(id:Long){
        withContext(Dispatchers.IO) {
            movieDAO.deleteMovieById(id)
        }
    }

     fun getAll() = movieDAO.getAll()

    fun addMovietoDb(movie: Movie){
        launch {
            addMovie(movie)
        }
    }

    fun delete(id:Long){
        launch {
            deleteMovie(id)
        }
    }

    suspend fun isfavourite(id:Long):Boolean{
        return withContext(Dispatchers.IO){
            movieDAO.isFavourite(id).isNotEmpty()
        }
    }
}