package com.example.movieapp.screens.favouriteScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.MovieApplication
import com.example.movieapp.R
import com.example.movieapp.databinding.FavouriteFragmentBinding
import com.example.movieapp.model.Result
import com.example.movieapp.screens.viewpage.FragmentMainDirections


class FavouriteScreenFragment :Fragment(){
    lateinit var binding: FavouriteFragmentBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FavouriteMovieAdapter

    private val favViewMod by viewModels<favouriteViewMod>{
        FavouriteViewModelFactory((requireContext().applicationContext as MovieApplication).movieRepoInterface)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.favourite_fragment,container,false)

        recyclerView = binding.favouriteRecycler

        favViewMod.getallMovies()?.observe(this, Observer {
            adapter.movieArray = it as ArrayList<Result>
            adapter.notifyDataSetChanged()
        })

        adapter = FavouriteMovieAdapter(this.context!!){
                movies->
            val action= FragmentMainDirections.actionMovieFragmentToSingleFavouriteFragment(movies)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter
        return binding.root
    }


}