package com.example.movieapp.screens.movieScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.database.Movie
import com.example.movieapp.model.database.Result
import com.example.movieapp.model.repository.ApiMovieRepository
import com.example.movieapp.model.repository.RoomDatabaseRepository
import kotlinx.coroutines.*
import java.lang.Exception

class MovieViewModel(application: Application) :AndroidViewModel(application){

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
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun getAll(){
        roomDatabaseRepository.getAll()
    }

    fun insert(movie: Movie){
        roomDatabaseRepository.addMovietoDb(movie)
    }

    fun delete(id:Long){
        roomDatabaseRepository.delete(id)
    }

    suspend fun mapFavourite(movie: List<Result>):List<Result>{
        return movie.map {
            it.isFavourite = roomDatabaseRepository.isfavourite(it.id)
            it
        }
    }
}