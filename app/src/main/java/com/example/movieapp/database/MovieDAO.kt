package com.example.movieapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.model.Result

@Dao
interface MovieDAO {
    @Query("SELECT * FROM favouritemovies ORDER BY uid DESC")
    fun getAll():LiveData<List<Result>>

    @Query("SELECT * FROM favouritemovies WHERE uid IN (:movieId)")
    fun isFavourite(movieId: Long): List<Result>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg movie: Result)

    @Query("DELETE FROM favouritemovies WHERE uid = :movieId")
    fun deleteMovieById(movieId: Long): Int

//    @Query("SELECT * FROM favouritemovies WHERE isFavourite =:favourite")
//    fun favouritelike(favourite:Boolean):LiveData<List<Result>>

}