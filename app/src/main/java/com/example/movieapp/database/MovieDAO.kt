package com.example.movieapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MovieDAO {
    @Query("SELECT * FROM favouriteMovies ORDER BY uid DESC")
    fun getAll():LiveData<List<Result>>

    @Query("SELECT * FROM favouriteMovies WHERE uid IN (:movieId)")
    fun isFavourite(movieId: Long): List<Result>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg movie: Result)

    @Query("DELETE FROM favouriteMovies WHERE uid = :movieId")
    fun deleteMovieById(movieId: Long): Int


}