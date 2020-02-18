package com.example.movieapp.screens.favouriteScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.FavouriteFragmentBinding
import com.example.movieapp.model.database.Movie
import kotlinx.coroutines.runBlocking


class FavouriteScreenFragment :Fragment(){
    lateinit var binding: FavouriteFragmentBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FavouriteMovieAdapter
//    lateinit var dbMovies:ArrayList<Movie>
    lateinit var viewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.favourite_fragment,container,false)

        viewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        recyclerView = binding.favouriteRecycler

        viewModel.allFavourite.observe(this, Observer {
            runBlocking {
                adapter.movieArray = it as ArrayList<Movie>
                Log.e("Favourite",it.toString())
                adapter.notifyDataSetChanged()
            }
        })
        adapter = FavouriteMovieAdapter(this.context!!)
        recyclerView.adapter = adapter

        return binding.root
    }


}