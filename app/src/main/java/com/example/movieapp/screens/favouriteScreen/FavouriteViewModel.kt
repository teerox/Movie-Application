package com.example.movieapp.screens.favouriteScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.database.Movie
import com.example.movieapp.model.repository.RoomDatabaseRepository
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application):AndroidViewModel(application){

    private var repository = RoomDatabaseRepository(application)
    private var allFavouriteMovies:LiveData<List<Movie>>

    init {
        allFavouriteMovies = repository.allFavouriteMovie
    }

    fun insert(movie: Movie) = viewModelScope.launch {
        repository.addMovie(movie)
    }
}