package com.example.movieapp.screens.moviescreen
//
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.movieapp.model.Result
//import com.example.movieapp.screens.movie.movieviewmodel.MovieViewModel
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.runBlocking
//import kotlinx.coroutines.test.runBlockingTest
//import org.hamcrest.Matchers
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.robolectric.annotation.Config
//
//@RunWith(AndroidJUnit4::class)
//@Config( manifest=Config.NONE)
//class MovieViewModelTest{
//
//    @Mock
//    lateinit var viewModel: MovieViewModel
//    private val movie1 = Result(
//        "https://movie1.jpg",
//        "https://movie2.jpg",
//        "Title1",
//        2.2,
//        "Movie1Overview",
//        "Jan 2022",
//        true,
//        1
//    )
//    private val movie2 = Result(
//        "https://movie2.jpg",
//        "https://movie3.jpg",
//        "Title2",
//        4.5,
//        "Movie2Overview",
//        "Jan 2020",
//        false,
//        2
//    )
//    private val movie3 = Result(
//        "https://movie3.jpg",
//        "https://movie1.jpg",
//        "Title3",
//        8.6,
//        "Movie3Overview",
//        "Jan 2026",
//        false,
//        3
//    )
//    private val movie4 = Result(
//        "https://movie3.jpg",
//        "https://movie1.jpg",
//        "Title4",
//        8.6,
//        "Movie3Overview",
//        "Jan 2026",
//        false,
//        4
//    )
//    private val movie5 = Result(
//        "https://movie3.jpg",
//        "https://movie1.jpg",
//        "Title5",
//        8.6,
//        "Movie3Overview",
//        "Jan 2026",
//        true,
//        5
//    )
//    private lateinit var movieRepositoryForTest: FakeRepositoryForTest
//    private lateinit var remoteDataSource: FakeDataSource<Result>
//    private lateinit var localDataSource: FakeDataSource<Result>
//    private val localTasks = mutableListOf(movie1, movie2,movie3,movie4).sortedBy { it.uid }
//
//
//    @Before
//    fun setUp(){
//        remoteDataSource = FakeDataSource(localTasks as MutableList<Result>)
//        localDataSource = FakeDataSource(localTasks.toMutableList())
//        localTasks.toMutableList().add(movie5)
//        localTasks.toMutableList().size
//        movieRepositoryForTest = FakeRepositoryForTest(localDataSource,remoteDataSource)
//        viewModel =
//            MovieViewModel(
//                movieRepositoryForTest
//            )
//    }
//
//
//
//    @Test
//    fun search_should_return_a_List(){
//        val array = listOf(movie1)
//       val result =  viewModel.search(array,"o")
//        assertThat(result, Matchers.equalTo(array))
//    }
//
//    @Test
//    fun getMoviesLocal_Return_Movies_From_Local_DataSource()= runBlocking {
//        val result = movieRepositoryForTest.getFavouriteMovies()
//        assertThat(result.value!!.size, Matchers.equalTo(1))
//    }
//
//    @Test
//    fun mapFavourite_Returns_true_if_made_favourite(){
//        runBlocking {
//            val result = movieRepositoryForTest.isfavourite(movie1.uid)
//            assertEquals(true, result )
//        }
//    }
//
//    @Test
//    fun mapFavourite_Returns_false_if_not_made_favourite(){
//        runBlocking {
//            val result = movieRepositoryForTest.isfavourite(movie2.uid)
//            assertEquals(false, result )
//        }
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun saveMovies_should_increase_the_size_of_my_array()= runBlockingTest{
//        movieRepositoryForTest.saveMovie(movie5)
//        assertEquals(true,movieRepositoryForTest.saveMovie(movie5))
//    }

//}