package com.example.movieapp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class],version = 2,exportSchema = false)
abstract class MovieDatabase:RoomDatabase(){
    abstract fun MovieDao():MovieDAO

    companion object {
        private var instance: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase? {

//            val Migration_1_2:Migration = object : Migration(1,2){
//                override fun migrate(database: SupportSQLiteDatabase) {
//                    database.execSQL("CREATE TABLE IF NOT EXISTS `aboutPoke` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `pokemonname` TEXT, `weight` INTEGER, `ability` TEXT, `moves`, TEXT )")
//                }
//            }

            if (instance == null){
                println("new")
                instance = Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java, "pokemon-db"
                ).fallbackToDestructiveMigration().build()
            }
            println("old")
            return instance
        }
        fun destroyInstance() {
            instance = null
        }
    }

}