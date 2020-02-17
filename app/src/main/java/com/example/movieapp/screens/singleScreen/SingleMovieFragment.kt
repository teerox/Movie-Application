package com.example.movieapp.screens.singleScreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.DataBinderMapperImpl
import com.example.movieapp.R
import com.example.movieapp.databinding.SingleMovieFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class SingleMovieFragment : Fragment() {

    lateinit var binding: SingleMovieFragmentBinding
    lateinit var viewModel: SingleScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = DataBindingUtil.inflate(inflater,R.layout.single_movie_fragment,container,false)
       viewModel = ViewModelProvider(this).get(SingleScreenViewModel::class.java)
        var args = SingleMovieFragmentArgs.fromBundle(arguments!!)
       val eachMovie = args.singlemovie
        binding.singleMovie = eachMovie
        val imageUrl = eachMovie.posterPath
        Glide.with(this).asBitmap().error(R.drawable.banner33_2x).load(imageUrl).into(binding.imageView3)
        return binding.root
        }



}
