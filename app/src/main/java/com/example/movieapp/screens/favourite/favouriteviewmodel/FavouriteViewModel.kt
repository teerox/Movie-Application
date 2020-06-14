package com.example.movieapp.screens.favourite.favouriteviewmodel

import androidx.lifecycle.*
import com.example.movieapp.model.Result
import com.example.movieapp.repository.MovieRepository

//
//class FavouriteViewModel(application: Application) :AndroidViewModel(application) {
//
//    private var movieRepository = ApiMovieRepository
//    private var roomDatabaseRepository = RoomDatabaseRepository(application)
//    private val viewModelJob = Job()
//    private val viewModeScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//
//    private var _allMovies = MutableLiveData<List<Result>>()
//    val allMovies
//        get() = _allMovies
//
//
//    init {
//        viewModeScope.launch {
//            try {
//                _allMovies.value = movieRepository.getAllMovies()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    var allFavourite = roomDatabaseRepository.getAll()
//    fun insert(movie: Result) {
//        roomDatabaseRepository.addMovietoDb(movie)
//    }
//
//    fun delete(id: Long) {
//        roomDatabaseRepository.delete(id)
//    }
//}

class favouriteViewMod(var movieRepository: MovieRepository):ViewModel(){

    fun getallMovies():LiveData<List<Result>>?{
        return movieRepository.getFavouriteMovies()
    }
}