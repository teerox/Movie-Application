package com.example.movieapp.screens.favouriteScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.database.Movie
import com.example.movieapp.model.database.Result
import com.example.movieapp.model.repository.ApiMovieRepository
import com.example.movieapp.model.repository.RoomDatabaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class FavouriteViewModel(application: Application) :AndroidViewModel(application) {

    private var movieRepository = ApiMovieRepository
    private var roomDatabaseRepository = RoomDatabaseRepository(application)
    private val viewModelJob = Job()
    private val viewModeScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private var _allMovies = MutableLiveData<List<Result>>()
    val allMovies
        get() = _allMovies


    init {
        viewModeScope.launch {
            try {
                _allMovies.value = movieRepository.getAllMovies()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    var allFavourite = roomDatabaseRepository.getAll()
    fun insert(movie: Movie) {
        roomDatabaseRepository.addMovietoDb(movie)
    }

    fun delete(id: Long) {
        roomDatabaseRepository.delete(id)
    }
}