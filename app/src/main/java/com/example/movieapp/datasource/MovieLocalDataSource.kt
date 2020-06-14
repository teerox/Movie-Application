package com.example.movieapp.datasource

import androidx.lifecycle.LiveData
import com.example.movieapp.database.MovieDAO
import com.example.movieapp.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieLocalDataSource internal constructor(private val movieDAO: MovieDAO,private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):
    MovieDataSource<Result> {

    override fun getLocal(): LiveData<List<Result>> {
      return movieDAO.getAll()
    }

    override suspend fun save(item: Result) {
        movieDAO.insertAll(item)
    }

    override suspend fun deleteAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(item: Long) {
       movieDAO.deleteMovieById(item)
    }

    override suspend fun getfav(id:Long) :Boolean {
        return withContext(Dispatchers.IO) {
            movieDAO.isFavourite(id).isNotEmpty()
        }
    }


}