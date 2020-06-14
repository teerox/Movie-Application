package com.example.movieapp.screens.favourite.favouriteviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.repository.MovieRepository

class FavouriteViewModelFactory(private val tasksRepository: MovieRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>) = (favouriteViewMod(
        tasksRepository
    ) as T)
}