package com.example.movieapp.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Result::class],version = 2, exportSchema = false)
abstract class MovieDatabase:RoomDatabase(){
    abstract fun movieDao():MovieDAO

}