package com.example.movieapp.datasource

import androidx.lifecycle.LiveData

interface MovieDataSource<T> {

    fun getLocal(): LiveData<List<T>>

    suspend fun getAll(): List<T>?{
        return listOf<T>()
    }

    suspend fun save(item: T)

    suspend fun deleteAll()

    suspend fun delete(item: Long)

    suspend fun getfav(id:Long):Boolean


//
//    suspend fun getVideo(id:Long):List<T>{
//        return listOf<T>()
//    }

}