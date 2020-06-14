package com.example.movieapp.screens.movie.movieviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.repository.MovieRepository

class MovieViewModelFactory (private val tasksRepository:MovieRepository):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>) = (MovieViewModel(
        tasksRepository
    ) as T)
}