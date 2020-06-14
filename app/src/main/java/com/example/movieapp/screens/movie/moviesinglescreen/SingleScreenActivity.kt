package com.example.movieapp.screens.movie.moviesinglescreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.example.movieapp.MovieApplication
import com.example.movieapp.databinding.SingleMovieFragmentBinding
import com.example.movieapp.screens.movie.movieviewmodel.MovieViewModel
import com.example.movieapp.screens.movie.movieviewmodel.MovieViewModelFactory
import com.example.movieapp.screens.moviesinglescreen.SingleScreenActivityArgs
import com.example.movieapp.utils.Utils
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.single_movie_fragment.*
import kotlinx.coroutines.runBlocking

class SingleScreenActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener{

    private lateinit var binding: SingleMovieFragmentBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory((this.applicationContext as MovieApplication).movieRepoInterface)
    }
    private val API_KEY = "AIzaSyDGJK88FBPM7nbSIpunXcls5ReW3QlWKuE"
    val args: SingleScreenActivityArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            binding = SingleMovieFragmentBinding.inflate(layoutInflater)
            setSupportActionBar(binding.toolbarsingle)
            val view = binding.root
            setContentView(view)

            val frag = supportFragmentManager.findFragmentById(youtube.id) as YouTubePlayerSupportFragment?

            val eachMovies = args.singleMoviesVideos
            binding.singleMovie = eachMovies
            frag?.initialize(API_KEY,this)

            val voteAverage = (eachMovies.voteAverage)
            val rating = Utils().rating(voteAverage)
            binding.ratingBar4.rating = rating
            binding.ratingnum.text = "Rating: $rating/5"
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            Toast.makeText(this,"No Internet",Toast.LENGTH_LONG).show()
        }
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        try {
            runBlocking {
                val eachMovies = args.singleMoviesVideos
                val a = viewModel.getVideos(eachMovies)
                val videoString = a[0].key
                p1?.cueVideo(videoString)
            }
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            Toast.makeText(this,"No Internet",Toast.LENGTH_LONG).show()
        }
    }
    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
      val errorMessage = p1.toString()
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        Log.d("errorMessage:", errorMessage)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}