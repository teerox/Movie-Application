package com.example.movieapp.screens.movie.movieviewmodel

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieResult
import com.example.movieapp.model.VideoResult
import com.example.movieapp.repository.DefaultMoviesRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val movieRepository:DefaultMoviesRepository){

    var id = 1
    private var totalPages = MutableLiveData<Int>()
    private val randomNumber = (0..totalPages.value!!).random()


    private var _allPopularMovies = MutableLiveData<Movie>()
    val allPopularMovies: LiveData<Movie>
        get() = _allPopularMovies


    private var _allTopRatedMovies = MutableLiveData<Movie>()
    val allTopRatedMovies: LiveData<Movie>
        get() = _allTopRatedMovies


    private var _allUpcomingMovies = MutableLiveData<Movie>()
    val allUpcomingMovies: LiveData<Movie>
        get() = _allUpcomingMovies


    private var _allNowPlayingMovie = MutableLiveData<Movie>()
    val allNowPlayingMovie: LiveData<Movie>
        get() = _allNowPlayingMovie



    init {
        getAllPopularMovies()
        getAllTopRatedMovies()
        getAllUpcomingMovies()
        getAllTopRatedMovies()
        getAllNowPlayingMovie()
    }



     private fun getAllPopularMovies(){
        movieRepository.getAllPopularMovies(id).let {
            if (it != null) {
               totalPages.value = it.value?.total_pages!!
                _allPopularMovies = it
            }
        }

    }



    fun increaseNumber(){
        id = randomNumber
    }


    private fun getAllTopRatedMovies(){
        movieRepository.getAllTopRatedMovies(id).let {
            if (it != null) {
                totalPages.value = it.value?.total_pages!!
                _allTopRatedMovies = it
            }
        }

    }


    private fun getAllUpcomingMovies(){
        movieRepository.getAllUpcomingMovies(id).let {
            if (it != null) {
                totalPages.value = it.value?.total_pages!!
                _allUpcomingMovies = it
            }
        }

    }



    private fun getAllNowPlayingMovie(){
        movieRepository.getAllNowPlayingMovie(id).let {
            if (it != null) {
                totalPages.value = it.value?.total_pages!!
                _allNowPlayingMovie = it
            }
        }

    }



    @SuppressLint("DefaultLocale")
    fun search(searchItem:String): MutableLiveData<Movie>? {
        return movieRepository.getSearchedMovies(id,searchItem)
    }



    fun getVideos(movie: MovieResult): MutableLiveData<VideoResult>? {
            return movieRepository.getAllVideos(movie)
    }



    private suspend fun saveToFavourite(movie: MovieResult){
        movieRepository.saveMovie(movie)
    }


    suspend fun mapFavourite(favouriteMovie: List<MovieResult>):List<MovieResult>{
        return favouriteMovie.map {
            it.isFavourite = movieRepository.isFavourite(it.id)
            it
        }
    }


    suspend fun like(item: MovieResult, view :ImageView){
        if (!item.isFavourite) {
            item.isFavourite = true
            view.setImageResource(R.drawable.ic_favorite_black_24dp)
            coroutineScope { saveToFavourite(item) }
            Snackbar.make(view, "Movie Added to Favourites", Snackbar.LENGTH_LONG).show()
        } else {
            item.isFavourite = false
            view.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            coroutineScope {
                movieRepository.deleteMovie(item.id)
            }
            Snackbar.make(view, "Movie Removed from Favourites", Snackbar.LENGTH_LONG).show()
        }
    }


    suspend fun unLike(item: MovieResult, view: ImageView){
        if (item.isFavourite) {
            item.isFavourite = false
            coroutineScope {
                movieRepository.deleteMovie(item.id)
            }
            view.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            Snackbar.make(view, "Movie Removed from Favourites", Snackbar.LENGTH_LONG).show()
        } else {
            item.isFavourite = true
            coroutineScope { movieRepository.saveMovie(item) }
            view.setImageResource(R.drawable.ic_favorite_black_24dp)
            Snackbar.make(view, "Movie Removed from Favourites", Snackbar.LENGTH_LONG).show()
        }

    }

}