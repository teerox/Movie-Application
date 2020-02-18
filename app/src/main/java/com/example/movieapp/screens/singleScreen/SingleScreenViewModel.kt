package com.example.movieapp.screens.singleScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.movieapp.model.repository.ApiMovieRepository

class SingleScreenViewModel(application: Application):AndroidViewModel(application){
    private var singlerepository = ApiMovieRepository
}