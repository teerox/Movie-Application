package com.example.movieapp.di

import com.example.movieapp.di.modules.DatabaseModule
import com.example.movieapp.di.modules.NetworkModule
import com.example.movieapp.screens.favourite.favouritescreen.FavouriteScreenFragment
import com.example.movieapp.screens.movie.moviescreen.MovieFragment
import com.example.movieapp.screens.favourite.favouritesinglescreen.FavouriteSingleFragment
import com.example.movieapp.screens.movie.moviesinglescreen.SingleMovieFragment
import com.example.movieapp.screens.main.FragmentMain
import com.example.movieapp.screens.main.MainActivity
import com.example.movieapp.screens.main.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: SplashActivity)
    fun inject(fragment: FragmentMain)
    fun inject(fragment: FavouriteSingleFragment)
    fun inject(fragment: SingleMovieFragment)
    fun inject(fragment: MovieFragment)
    fun inject(fragment: FavouriteScreenFragment)


}