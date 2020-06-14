package com.example.movieapp

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.example.movieapp.database.MovieDatabase
import com.example.movieapp.repository.DefaultMoviesRepository
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.datasource.MovieLocalDataSource
import com.example.movieapp.di.MovieRemoteDataSource

object ServiceLocator {
    private var database:MovieDatabase? = null

    @Volatile
    var moviesRepoInterface:MovieRepository? =null
        @VisibleForTesting

    private var lock = Any()

    fun provideMovieRepository(context: Context):MovieRepository{
        synchronized(this){
            return moviesRepoInterface?: createMoveRepository(context)
        }
    }

    private fun createMoveRepository(context: Context):MovieRepository{
        val newRepository =DefaultMoviesRepository(createLocalDataSource(context),
            MovieRemoteDataSource()
        )

        moviesRepoInterface = newRepository
        return newRepository
    }

    private fun createLocalDataSource(context: Context): MovieLocalDataSource {
        val database = database?:createDataBase(context)
        return MovieLocalDataSource(database.movieDao())
    }

    private fun createDataBase(context: Context):MovieDatabase{
        val result = Room.databaseBuilder(context, MovieDatabase::class.java,"MovieDb").fallbackToDestructiveMigration()
            .build()
        database = result
        return result
    }

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            moviesRepoInterface = null
        }
    }

}