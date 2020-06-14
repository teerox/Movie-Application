package com.example.movieapp.screens.movie.movieviewmodel

import android.annotation.SuppressLint
import android.support.v4.os.IResultReceiver
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.R
import com.example.movieapp.repository.DefaultMoviesRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val movieRepository:DefaultMoviesRepository){

    val items: MutableLiveData<List<Result>> = MutableLiveData()

    init {
        getMovies()
    }

     private fun getMovies(){
         runBlocking {
             items.postValue(movieRepository.getMovies())
         }
    }


    private suspend fun saveToFavourite(movie: Result){
        movieRepository.saveMovie(movie)
    }

    suspend fun mapFavourite(favouriteMovie: List<Result>):List<Result>{
        return favouriteMovie.map {
            it.isFavourite = movieRepository.isFavourite(it.uid)
            it
        }
    }


    suspend fun getVideos(movie: Result): List<ResultMv>{
        var data = listOf<ResultMv>()
      return withContext(Dispatchers.IO){
            data= movieRepository.getVideos(movie)
            data
        }
    }






    @SuppressLint("DefaultLocale")
    fun search(resultArrayList: List<Result>, searchItem:String):List<Result>{
        return resultArrayList.filter { it ->
            it.title.contains(searchItem.toLowerCase().trim{ char -> char <= ' ' },true)
        }
    }

    suspend fun like(item: Result, view :ImageView){
        if (!item.isFavourite) {
            item.isFavourite = true
            view.setImageResource(R.drawable.ic_favorite_black_24dp)
            coroutineScope { saveToFavourite(item) }
            Snackbar.make(view, "Movie Added to Favourites", Snackbar.LENGTH_LONG).show()
        } else {
            item.isFavourite = false
            view.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            coroutineScope {
                movieRepository.deleteMovie(item.uid)
            }
            Snackbar.make(view, "Movie Removed from Favourites", Snackbar.LENGTH_LONG).show()
        }
    }

    suspend fun unLike(item: Result, view: ImageView){
        if (item.isFavourite) {
            item.isFavourite = false
            coroutineScope {
                movieRepository.deleteMovie(item.uid)
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