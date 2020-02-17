package com.example.movieapp.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movies ORDER BY uid DESC")
    fun getAll():LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: Int): List<Movie>

    @Insert
    fun insertAll(vararg movie: Movie)

    @Delete
    fun delete(movie: Movie)

}