package com.example.movieapp.screens.movieScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.Result
import com.example.movieapp.datasource.MovieDataSource

class FakeDataSource<T>(private var movies:MutableList<Result>):
    MovieDataSource<Result> {
    override fun getLocal(): LiveData<List<Result>> {
        val result = MutableLiveData<List<Result>>()
        val a = movies.filter {
            it as Result
            it.isFavourite
        }
        result.value = a
        return result
    }

    override suspend fun getAll(): List<Result>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override suspend fun deleteAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(item: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getfav(id: Long): Boolean {
        val a = movies
        a.forEach {
            it as Result
            if(id == it.uid && it.isFavourite) {
                return true
            }
        }
        return false
    }

    override suspend fun save(item: Result) {
        movies.add(item)
    }


}