package com.example.movieapp

import android.app.Application
import com.example.movieapp.repository.MovieRepository

class MovieApplication:Application(){
    val movieRepoInterface:MovieRepository
    get() = ServiceLocator.provideMovieRepository(this)

    override fun onCreate() {
        super.onCreate()
    }
}