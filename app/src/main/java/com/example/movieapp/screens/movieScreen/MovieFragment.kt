package com.example.movieapp.screens.movieScreen


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieFragmentBinding

import com.example.movieapp.model.database.Result
import com.example.movieapp.screens.viewpage.FragmentMainDirections

import kotlinx.coroutines.runBlocking


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var binding: MovieFragmentBinding
    lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.movie_fragment, container, false)


        val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        recyclerView = binding.recyclerId


        val movies = viewModel.allMovies

        adapter = MovieAdapter(this.context!!, viewModel) { Result ->
            val action = FragmentMainDirections.actionMovieFragmentToSingleMovieFragment(Result)
            findNavController().navigate(action)
        }

        movies.observeForever {
            runBlocking {
                adapter.movieArray = it as ArrayList<Result>
                adapter.movieArray = viewModel.mapFavourite(it) as ArrayList<Result>
                Log.e("ggggg", it.toString())
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            }
        }

        recyclerView.adapter = adapter

        return binding.root
    }


}


