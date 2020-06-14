package com.example.movieapp.screens.movieScreen

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.R
import com.example.movieapp.model.Result
import com.example.movieapp.di.ResultMv
import com.example.movieapp.repository.MovieRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class MovieViewModel(private val movieRepository:MovieRepository):ViewModel(){

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
            it.isFavourite = movieRepository.isfavourite(it.uid)
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



//    suspend fun getVideos(movie: Result):List<ResultMv>{
////        return GlobalScope.async(Dispatchers.IO){
////            var videos = arrayListOf<ResultMv>()
////            val base_url = "https://api.themoviedb.org/3/movie/${movie.uid}/videos?api_key=fb97e27952573c39dd8c56b40023750e&language=en-US"
////            val request = Request.Builder().url(base_url).build()
////            val client = OkHttpClient()
////            client.newCall(request).enqueue(object : Callback {
////                override fun onFailure(call: Call, e: IOException) {
////                    println("failllledddd ${e.message}")
////                }
////                override fun onResponse(call: Call, response: Response) {
////                    val body = response.body()?.string()
////                    val gson = GsonBuilder().create()
////                    val videoFeed = gson.fromJson(body, WelcomeMv::class.java)
////                    videos = videoFeed.results  as ArrayList<ResultMv>
////                    Log.e("Movies", videos.toString() )
////                }
////            })
////            videos
////        }.await()
//
//
//        }




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