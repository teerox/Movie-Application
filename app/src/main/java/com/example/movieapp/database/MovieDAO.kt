package com.example.movieapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.model.MovieResult


@Dao
interface MovieDAO {
    @Query("SELECT * FROM favouriteMovies ORDER BY id DESC")
    fun getAll():LiveData<List<MovieResult>>

    @Query("SELECT * FROM favouriteMovies WHERE id IN (:movieId)")
    fun isFavourite(movieId: Long): List<MovieResult>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg movie: MovieResult)

    @Query("DELETE FROM favouriteMovies WHERE id = :movieId")
    fun deleteMovieById(movieId: Long): Int


}