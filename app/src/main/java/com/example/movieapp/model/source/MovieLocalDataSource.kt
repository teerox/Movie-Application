package com.example.movieapp.model.source
//
//import androidx.lifecycle.LiveData
//import com.example.movieapp.domain.database.Movie
//import com.example.movieapp.domain.database.MovieDAO
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import java.lang.Exception
//
//class MovieLocalDataSource internal constructor(private val movieDAO: MovieDAO,private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):MovieDataSource<Movie>{
//    override fun getLocal(): LiveData<List<Movie>> {
//
//        return movieDAO.getAll()
//         }
//
//    override suspend fun save(item: Movie) {
//        return movieDAO.insertAll(item)
//    }
//
//
//
//    override suspend fun delete(item: Movie) {
//    movieDAO.deleteMovieById(item.uid)
//    }
//
//
//}