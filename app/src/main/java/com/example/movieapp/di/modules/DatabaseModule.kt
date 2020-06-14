package com.example.movieapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.movieapp.MyApplication
import com.example.movieapp.database.MovieDAO
import com.example.movieapp.database.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private var application: MyApplication){

    @Singleton
    @Provides
    fun context(): Context {
        return application
    }

    @Singleton
    @Provides
    internal fun provideRoomDatabase(context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase ::class.java, "MovieDb")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: MovieDatabase ): MovieDAO {
        return database.movieDao()
    }

}