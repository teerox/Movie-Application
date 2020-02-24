package com.example.movieapp.screens.singleFavouriteScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.FavouriteSingleFragmentBinding
import com.example.movieapp.model.api.MyRetrofitBuilder
import com.example.movieapp.utils.Utils

class SingleFavouriteFragment :Fragment(){
    private lateinit var singleFragmentBinding: FavouriteSingleFragmentBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        singleFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.favourite_single_fragment,container,false)
        val args = SingleFavouriteFragmentArgs.fromBundle(arguments!!)
        val eachMovie = args.sinMoves


        singleFragmentBinding.singlefavourite = eachMovie
        val rating = Utils().rating(eachMovie.movieRating.toDouble())
        singleFragmentBinding.favouriteRatingBar4.rating= rating
        singleFragmentBinding.favouriteRatingNum.text= "Rating: $rating/5"


        val imageUrl = eachMovie.backdropImg
        Glide.with(this).load(MyRetrofitBuilder.IMAGE_BASE_URL + "original" +imageUrl).into(singleFragmentBinding.imageView3)
        return singleFragmentBinding.root
    }


}

