package com.example.movieapp.screens.moviescreen
//
//import androidx.lifecycle.LiveData
//import com.example.movieapp.model.Result
//import com.example.movieapp.repository.MovieRepository
//
//class FakeRepositoryForTest(private var localDataSource: FakeDataSource<Result>,
//                            private var remoteDataSource: FakeDataSource<Result>):MovieRepository{
//    override suspend fun saveMovie(movie: Result) {
//        localDataSource.save(movie)
//    }
//
//    override fun getFavouriteMovies(): LiveData<List<Result>> {
//        return localDataSource.getLocal()
//    }
//
//    override suspend fun isfavourite(id: Long): Boolean {
//       return localDataSource.getfav(id)
//    }
//
//}