package com.example.movieapp.screens.movieScreen


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.RecyclerFragmentBinding
import com.example.movieapp.model.database.Result
import kotlinx.coroutines.runBlocking


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var binding:RecyclerFragmentBinding
    lateinit var adapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.recycler_fragment,container,false)
        val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        recyclerView = binding.recyclerId
        val movies = viewModel.allMovies

        adapter = MovieAdapter(this.context!!) {
            val action = MovieFragmentDirections.actionMovieFragmentToSingleMovieFragment(it)
            binding.root.findNavController().navigate(action)
        }
        movies.observe(viewLifecycleOwner, Observer<List<Result>> {
            runBlocking {
                adapter.movieArray = it as ArrayList<Result>

                Log.e("ggggg",it.toString())
                adapter.notifyDataSetChanged()
            }
        })


        recyclerView.adapter = adapter

        return binding.root
    }



}


