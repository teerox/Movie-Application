package com.example.movieapp.model.repository

import android.util.Log
import com.example.movieapp.model.api.MyRetrofitBuilder
import com.example.movieapp.model.database.Movie
import com.example.movieapp.model.database.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Callback
import retrofit2.await
import java.lang.Error

object ApiMovieRepository {

    private val api = MyRetrofitBuilder.provideMovieApi()

    suspend fun getAllMovies():List<Result>{
        var data = listOf<Result>()
        withContext(Dispatchers.IO){
            try {
                val allmovie = api.getAllmoviesAsync().await().results
            data = allmovie
                Log.e("Result", data.toString())
            }catch (e:Error){
                e.printStackTrace()
            }
        }
        return data
    }

}