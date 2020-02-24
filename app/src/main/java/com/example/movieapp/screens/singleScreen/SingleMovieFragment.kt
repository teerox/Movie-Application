package com.example.movieapp.screens.singleScreen


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.SingleMovieFragmentBinding
import com.example.movieapp.model.api.MyRetrofitBuilder
import com.example.movieapp.utils.Utils

/**
 * A simple [Fragment] subclass.
 */
class SingleMovieFragment : Fragment() {

    lateinit var binding: SingleMovieFragmentBinding
    lateinit var viewModel: SingleScreenViewModel
    lateinit var  menuItem:MenuItem
    private lateinit var navController: NavController
    private var utils = Utils()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding = DataBindingUtil.inflate(inflater,R.layout.single_movie_fragment,container,false)
       viewModel = ViewModelProvider(this).get(SingleScreenViewModel::class.java)

        val args = SingleMovieFragmentArgs.fromBundle(arguments!!)

        val eachMovie = args.singlemovie
        binding.singleMovie = eachMovie

        val voteAverage = (eachMovie.voteAverage)
        val rating =Utils().rating(voteAverage)
        binding.ratingBar4.rating = rating
        binding.ratingnum.text = "Rating: $rating/5"

        val imageUrl = eachMovie.backdropPath
        Glide.with(this).load(MyRetrofitBuilder.IMAGE_BASE_URL + "original" +imageUrl).into(binding.imageView3)
        return binding.root
        }


}
