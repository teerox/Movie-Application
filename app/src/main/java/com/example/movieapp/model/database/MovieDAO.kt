package com.example.movieapp.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movies ORDER BY uid DESC")
    fun getAll():LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE uid IN (:movieId)")
    fun isFavourite(movieId: Long): List<Movie>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movie: Movie)

    @Query("DELETE FROM movies WHERE uid = :movieId")
    fun deleteMovieById(movieId: Long): Int



}