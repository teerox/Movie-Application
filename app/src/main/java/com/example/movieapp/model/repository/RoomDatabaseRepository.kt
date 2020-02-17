package com.example.movieapp.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.movieapp.model.database.Movie
import com.example.movieapp.model.database.MovieDatabase

class RoomDatabaseRepository(var context: Context) {

    var database = MovieDatabase.getInstance(context)?.MovieDao()
    suspend fun addMovie(movie: Movie){
        database!!.insertAll(movie)
    }
    val allFavouriteMovie:LiveData<List<Movie>> = database!!.getAll()

}