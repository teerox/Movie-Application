package com.example.movieapp.screens.favourite.favouriteviewmodel

import android.widget.ImageView
import androidx.lifecycle.*
import com.example.movieapp.R
import com.example.movieapp.model.MovieResult
import com.example.movieapp.repository.DefaultMoviesRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class favouriteViewModel @Inject constructor(private val movieRepository: DefaultMoviesRepository){

    fun getAllLocalMovies():LiveData<List<MovieResult>>?{
        return movieRepository.getLocal()
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