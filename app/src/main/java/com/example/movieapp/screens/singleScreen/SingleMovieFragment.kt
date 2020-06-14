package com.example.movieapp.screens.singleScreen


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.example.movieapp.MovieApplication
import com.example.movieapp.R
import com.example.movieapp.databinding.SingleMovieFragmentBinding
import com.example.movieapp.screens.movieScreen.MovieViewModel
import com.example.movieapp.screens.movieScreen.MovieViewModelFactory
import com.example.movieapp.utils.Utils
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.coroutines.runBlocking


/**
 * A simple [Fragment] subclass.
 */
class SingleMovieFragment : Fragment() {

    lateinit var binding: SingleMovieFragmentBinding
    lateinit var  menuItem:MenuItem
    private lateinit var navController: NavController
    lateinit var youtubeplayer: YouTubePlayerView
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory((requireContext().applicationContext as MovieApplication).movieRepoInterface)
    }
    private val API_KEY = "AIzaSyDGJK88FBPM7nbSIpunXcls5ReW3QlWKuE"
    private val VIDEO_ID = "a4NT5iBFuZs"

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding = DataBindingUtil.inflate(inflater, R.layout.single_movie_fragment,container,false)
           // viewModel = ViewModelProvider(this).get(SingleScreenViewModel::class.java)
       // val args = SingleMovieFragmentArgs.fromBundle(arguments!!)
        var args = SingleScreenActivityArgs.fromBundle(arguments!!)

        val eachMovie = args.singleMoviesVideos
        binding.singleMovie = eachMovie
      // youtubeplayer = youtubeplayer.findViewById(R.id.youtube)

        val youtubeFragment =
            fragmentManager!!.findFragmentById(R.id.youtube) as YouTubePlayerFragment?

            runBlocking {
                val a = viewModel.getVideos(eachMovie)
                Log.e("MovieVideossss", a.toString())
                val videoString = a[0].key
                youtubeFragment?.initialize(API_KEY,
                    object : YouTubePlayer.OnInitializedListener {
                        override fun onInitializationSuccess(
                            provider: YouTubePlayer.Provider,
                            youTubePlayer: YouTubePlayer, b: Boolean
                        ) { // do any work here to cue video, play video, etc.
                            youTubePlayer.cueVideo(videoString)
                        }

                        override fun onInitializationFailure(
                            provider: YouTubePlayer.Provider,
                            youTubeInitializationResult: YouTubeInitializationResult
                        ) {
                        }
                    })
            }



       // val youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance()

        val voteAverage = (eachMovie.voteAverage)
        val rating =Utils().rating(voteAverage)
        binding.ratingBar4.rating = rating
        binding.ratingnum.text = "Rating: $rating/5"

       // val imageUrl = eachMovie.backdropPath
       // Glide.with(this).load(MyRetrofitBuilder.IMAGE_BASE_URL + "original" +imageUrl).into(binding.imageView3)
        return binding.root
        }


}
